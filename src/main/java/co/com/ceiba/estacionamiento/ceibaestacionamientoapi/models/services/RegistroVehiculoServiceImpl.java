package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.dao.IRegistroVehiculoDao;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.RegistroVehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.util.TipoVehiculo;

@Service("registroVehiculoService")
public class RegistroVehiculoServiceImpl implements IRegistroVehiculoService{

	private IRegistroVehiculoDao registroVehiculoDao;
	
	@Autowired
	public RegistroVehiculoServiceImpl(IRegistroVehiculoDao registroVehiculoDao) {
		this.registroVehiculoDao = registroVehiculoDao;
	}
	
	@Override
	@Transactional
	public void save(RegistroVehiculo registro) {
		registroVehiculoDao.save(registro);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<RegistroVehiculo> listarRegistros() {
		return (List<RegistroVehiculo>) registroVehiculoDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public int cantParqueaderosDisponibles(TipoVehiculo tipoVehiculo) {
		return registroVehiculoDao.countByTipoVehiculoAndFechaSalida(tipoVehiculo);
	}
	
	@Override
	@Transactional(readOnly=true)
	public RegistroVehiculo buscarVehiculoId(Long id) {
		return registroVehiculoDao.findVehiculoById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public RegistroVehiculo buscarvehiculoPlaca(String placa) {
		return registroVehiculoDao.findVehiculoByPlaca(placa);
	}

}
