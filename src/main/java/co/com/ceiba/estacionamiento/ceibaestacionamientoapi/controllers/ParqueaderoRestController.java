package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.controllers;


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

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.dominio.Vigilante;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.RegistroVehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.services.IRegistroVehiculoService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/parqueadero")
public class ParqueaderoRestController {

	@Autowired
	private Vigilante vigilante;
	
	@Autowired
	private IRegistroVehiculoService registroVehiculoService;
	
	@RequestMapping(value="/registrar", method = RequestMethod.POST)
	public ResponseEntity<RegistroVehiculo> registrarEntrada(@RequestBody RegistroVehiculo registro) {
		vigilante.registrarEntrada(registro);
		return new ResponseEntity<>(registro, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/facturar/{id}", method = RequestMethod.PUT)
	public ResponseEntity<RegistroVehiculo> generarTiquete(@PathVariable Long id, @RequestBody RegistroVehiculo registro) {
		vigilante.registrarSalida(id, registro);		
		return new ResponseEntity<>(registro, HttpStatus.OK);
	}
	
	@GetMapping(value="/listar")
	public ResponseEntity<List<RegistroVehiculo>> listarVehiculos() {
		List<RegistroVehiculo> tiquetes = registroVehiculoService.listarTiquetes();
        if (tiquetes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
		return new ResponseEntity<>(tiquetes, HttpStatus.OK);

	}
	
	@GetMapping(value="/registro/{id}")
	public ResponseEntity<RegistroVehiculo> buscarTiquete(@PathVariable Long id) {
		RegistroVehiculo tiquete = registroVehiculoService.buscarVehiculoId(id);
        if (null==tiquete) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
		return new ResponseEntity<>(tiquete, HttpStatus.OK);
 	}
}
