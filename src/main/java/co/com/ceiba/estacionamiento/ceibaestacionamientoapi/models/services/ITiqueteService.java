package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.services;

import java.util.List;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.Tiquete;

public interface ITiqueteService {
	
	public void save(Tiquete tiquete);

	public List<Tiquete> listarTiquetes();
	
	public long cantParqueaderosDisponibles(String tipoVehiculo);
	
	public Tiquete buscarVehiculoRegistrado(Long id);
	
	public Double calcularCosto(Tiquete tiquete);
	
	public Tiquete validarVehiculo(String placa);
	
}
