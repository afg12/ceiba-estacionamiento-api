package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.Parqueadero;

public interface IParqueaderoDao extends CrudRepository<Parqueadero, Long> {
	
	@Query("select count(*) from Parqueadero where p.tipo_vehiculo = ?1 AND p.fecha_salida IS NULL")
	public int findByTipoVehiculoAndFechaSalidaIsNull(String tipoVehiculo);
	
	@Query("select p from Parqueadero where p.placa = ?1")
	public Parqueadero findParqueaderoByPlaca(String placa);

}
