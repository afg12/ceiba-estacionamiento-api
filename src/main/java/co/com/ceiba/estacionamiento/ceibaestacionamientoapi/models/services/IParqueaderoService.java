package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.Parqueadero;

public interface IParqueaderoService {
	
	public void save(Parqueadero parqueadero);

	public List<Parqueadero> findAll();
	
	public int cantidadParqueaderosDisponibles(String tipoVehiculo);
	
	public boolean isVehiculoRegistrado(String placa);
	
}
