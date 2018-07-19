package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.services;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.util.TipoVehiculo;

public interface IVigilanteService {
	
	public boolean validarDisponibilidad(TipoVehiculo tipoVehiculo);
	
	public boolean validarPlaca(String placa);
}
