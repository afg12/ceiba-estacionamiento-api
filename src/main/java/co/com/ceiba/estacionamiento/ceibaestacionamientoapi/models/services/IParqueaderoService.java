package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.services;

import java.util.Calendar;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.util.TipoVehiculo;

public interface IParqueaderoService {
	
	public void validarDisponibilidad(TipoVehiculo tipoVehiculo);
	
	public void validarPlaca(String placa, Calendar calendar);
}
