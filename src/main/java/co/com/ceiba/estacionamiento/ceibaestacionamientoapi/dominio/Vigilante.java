package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.dominio;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.RegistroVehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.services.IParqueaderoService;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.services.IRegistroVehiculoService;

@Controller
public class Vigilante {
	
	@Autowired
	private IRegistroVehiculoService registroVehiculoService;

	@Autowired
	private IParqueaderoService parqueaderoService;

	public void registrarEntrada(RegistroVehiculo registro) {
		Calendar calendar = Calendar.getInstance();
		parqueaderoService.validarDisponibilidad(registro.getTipoVehiculo());
		parqueaderoService.validarPlaca(registro.getPlaca(), calendar);
		registroVehiculoService.save(registro);
	}
	
	public void registrarSalida(Long id, RegistroVehiculo registro) {
		RegistroVehiculo registroEncontrado = registroVehiculoService.buscarVehiculoId(id);
		registroEncontrado.setTotal(CalculadoraCosto.getInstance().calcularCosto(registro));
		registroVehiculoService.save(registroEncontrado);
	}

}
