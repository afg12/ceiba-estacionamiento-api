package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.dao;

import org.springframework.data.repository.CrudRepository;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.Parqueadero;

public interface IParqueaderoDao extends CrudRepository<Parqueadero, Long> {

}
