package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.services;

import java.util.List;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.Tiquete;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.util.TipoVehiculo;

public interface ITiqueteService {
	
	public void save(Tiquete tiquete);

	public List<Tiquete> listarTiquetes();
	
	public int cantParqueaderosDisponibles(TipoVehiculo tipoVehiculo);
	
	public Tiquete buscarVehiculoId(Long id);
	
	public Tiquete buscarvehiculoPlaca(String placa);
	
}
