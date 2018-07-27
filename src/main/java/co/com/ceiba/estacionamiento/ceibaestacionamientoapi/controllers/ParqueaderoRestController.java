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

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.Tiquete;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.services.ITiqueteService;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.services.IParqueaderoService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/parqueadero")
public class ParqueaderoRestController {

	@Autowired
	private ITiqueteService tiqueteService;
	
	@Autowired
	private IParqueaderoService parqueaderoService;
	
	@RequestMapping(value="/registrar", method = RequestMethod.POST)
	public ResponseEntity<Tiquete> registrarEntrada(@RequestBody Tiquete tiquete) {
		Calendar calendar = Calendar.getInstance();
		parqueaderoService.validarDisponibilidad(tiquete.getTipoVehiculo());
		parqueaderoService.validarPlaca(tiquete.getPlaca(), calendar);
			
		tiqueteService.save(tiquete);

		return new ResponseEntity<>(tiquete, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/facturar/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Tiquete> generarTiquete(@PathVariable Long id, @RequestBody Tiquete tiquete) {
		Tiquete tiqueteEncontrado = tiqueteService.buscarVehiculoRegistrado(id);
		tiqueteEncontrado.setTotal(tiqueteService.calcularCosto(tiquete));
		tiqueteService.save(tiqueteEncontrado);
		
		return new ResponseEntity<>(tiquete, HttpStatus.OK);
	}
	
	@GetMapping(value="/listar")
	public ResponseEntity<List<Tiquete>> listarVehiculos() {
		List<Tiquete> tiquetes = tiqueteService.listarTiquetes();
        if (tiquetes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
		return new ResponseEntity<>(tiquetes, HttpStatus.OK);

	}
	
	@GetMapping(value="/tiquete/{id}")
	public ResponseEntity<Tiquete> buscarTiquete(@PathVariable Long id) {
		Tiquete tiquete = tiqueteService.buscarVehiculoRegistrado(id);
        if (null==tiquete) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
		return new ResponseEntity<>(tiquete, HttpStatus.OK);
 	}
}
