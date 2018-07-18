package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.services;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.Tiquete;

public interface IVigilanteService {
	
	public boolean validarDisponibilidad(Tiquete tiquete);
	
	public boolean validarPlaca(String placa);
}
