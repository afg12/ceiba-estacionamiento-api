package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.RegistroVehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.util.TipoVehiculo;

public interface IRegistroVehiculoDao extends CrudRepository<RegistroVehiculo, Long> {

	@Query("select count(*) from RegistroVehiculo r where r.tipoVehiculo= ?1 and r.fechaSalida is null")
	public int countByTipoVehiculoAndFechaSalida(TipoVehiculo tipoVehiculo);
	
	@Query("select r from RegistroVehiculo r where r.id = :id")
	public RegistroVehiculo findVehiculoById(@Param("id") Long id);
	
	@Query("select r from RegistroVehiculo r where r.placa = ?1")
	public RegistroVehiculo findVehiculoByPlaca(String placa);
}
