package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.Tiquete;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.util.TipoVehiculo;

public interface ITiqueteDao extends CrudRepository<Tiquete, Long> {

	@Query("select count(*) from Tiquete t where t.tipoVehiculo= ?1 and t.fechaSalida is null")
	public int countByTipoVehiculoAndFechaSalida(TipoVehiculo tipoVehiculo);
	
	@Query("select t from Tiquete t where t.id = :id")
	public Tiquete findVehiculoById(@Param("id") Long id);
	
	@Query("select t from Tiquete t where t.placa = ?1")
	public Tiquete findVehiculoByPlaca(String placa);
}
