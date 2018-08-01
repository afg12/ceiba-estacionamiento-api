package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.services;

import java.util.List;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.RegistroVehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.util.TipoVehiculo;

public interface IRegistroVehiculoService {
	
	public void save(RegistroVehiculo registro);

	public List<RegistroVehiculo> listarRegistros();
	
	public int cantParqueaderosDisponibles(TipoVehiculo tipoVehiculo);
	
	public RegistroVehiculo buscarVehiculoId(Long id);
	
	public RegistroVehiculo buscarvehiculoPlaca(String placa);
	
}
