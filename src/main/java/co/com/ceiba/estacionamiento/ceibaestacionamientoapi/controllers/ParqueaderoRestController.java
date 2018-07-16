package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.Parqueadero;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.services.IParqueaderoService;

@RestController
@RequestMapping("/api")
public class ParqueaderoRestController {
	
	@Autowired
	private IParqueaderoService parqueaderoService;
	
	@RequestMapping(value="/registrar", method=RequestMethod.POST)
	public String guardar(Parqueadero parqueadero) {
		parqueaderoService.save(parqueadero);
		return null;
		
	}
	
	@RequestMapping(value="/listar")
	public List<Parqueadero> listar() {
		return parqueaderoService.findAll();
		
	}
}
