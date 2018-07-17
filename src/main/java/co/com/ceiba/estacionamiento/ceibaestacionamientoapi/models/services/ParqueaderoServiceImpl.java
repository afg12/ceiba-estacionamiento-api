package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.dao.IParqueaderoDao;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.Parqueadero;

@Service
public class ParqueaderoServiceImpl implements IParqueaderoService{

	@Autowired
	private IParqueaderoDao parqueaderoDao;
	
	@Override
	@Transactional
	public void save(Parqueadero vehiculo) {
		parqueaderoDao.save(vehiculo);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Parqueadero> findAll() {
		return (List<Parqueadero>) parqueaderoDao.findAll();
	}

	@Override
	public int cantidadParqueaderosDisponibles(String tipoVehiculo) {
		return parqueaderoDao.findByTipoVehiculoAndFechaSalidaIsNull(tipoVehiculo);
	}

	@Override
	public boolean isVehiculoRegistrado(String placa) {
		Parqueadero parqueadero = parqueaderoDao.findParqueaderoByPlaca(placa);
		return null!=parqueadero?false:true;
	}



}
