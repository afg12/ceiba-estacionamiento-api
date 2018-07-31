package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.dao.ITiqueteDao;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.Tiquete;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.util.TipoVehiculo;

@Service
public class TiqueteServiceImpl implements ITiqueteService{

	private ITiqueteDao tiqueteDao;
	
	@Autowired
	public TiqueteServiceImpl(ITiqueteDao tiqueteDao) {
		this.tiqueteDao = tiqueteDao;
	}
	
	@Override
	@Transactional
	public void save(Tiquete tiquete) {
		tiqueteDao.save(tiquete);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Tiquete> listarTiquetes() {
		return (List<Tiquete>) tiqueteDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public int cantParqueaderosDisponibles(TipoVehiculo tipoVehiculo) {
		return tiqueteDao.countByTipoVehiculoAndFechaSalida(tipoVehiculo);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Tiquete buscarVehiculoId(Long id) {
		return tiqueteDao.findVehiculoById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Tiquete buscarvehiculoPlaca(String placa) {
		return tiqueteDao.findVehiculoByPlaca(placa);
	}

}
