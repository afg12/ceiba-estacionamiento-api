package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.controllers;


import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.dominio.CalculadoraCosto;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.RegistroVehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.services.IRegistroVehiculoService;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.services.IParqueaderoService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/parqueadero")
public class ParqueaderoRestController {

	@Autowired
	private IRegistroVehiculoService tiqueteService;
	
	@Autowired
	private IParqueaderoService parqueaderoService;
	
	CalculadoraCosto calculadoraCosto = CalculadoraCosto.getInstance();
	
	@RequestMapping(value="/registrar", method = RequestMethod.POST)
	public ResponseEntity<RegistroVehiculo> registrarEntrada(@RequestBody RegistroVehiculo tiquete) {
		Calendar calendar = Calendar.getInstance();
		parqueaderoService.validarDisponibilidad(tiquete.getTipoVehiculo());
		parqueaderoService.validarPlaca(tiquete.getPlaca(), calendar);
			
		tiqueteService.save(tiquete);

		return new ResponseEntity<>(tiquete, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/facturar/{id}", method = RequestMethod.PUT)
	public ResponseEntity<RegistroVehiculo> generarTiquete(@PathVariable Long id, @RequestBody RegistroVehiculo tiquete) {
		RegistroVehiculo tiqueteEncontrado = tiqueteService.buscarVehiculoId(id);
		tiqueteEncontrado.setTotal(calculadoraCosto.calcularCosto(tiquete));
		tiqueteService.save(tiqueteEncontrado);
		
		return new ResponseEntity<>(tiquete, HttpStatus.OK);
	}
	
	@GetMapping(value="/listar")
	public ResponseEntity<List<RegistroVehiculo>> listarVehiculos() {
		List<RegistroVehiculo> tiquetes = tiqueteService.listarTiquetes();
        if (tiquetes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
		return new ResponseEntity<>(tiquetes, HttpStatus.OK);

	}
	
	@GetMapping(value="/tiquete/{id}")
	public ResponseEntity<RegistroVehiculo> buscarTiquete(@PathVariable Long id) {
		RegistroVehiculo tiquete = tiqueteService.buscarVehiculoId(id);
        if (null==tiquete) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
		return new ResponseEntity<>(tiquete, HttpStatus.OK);
 	}
}
