package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.services;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.exceptions.VehiculoException;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.dao.ITiqueteDao;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.util.TipoVehiculo;

@Service
public class VigilanteServiceImpl implements IVigilanteService{
	
	private static final String PLACA_NO_PERMITIDA = "A";
	
	private static final int CANT_MOTOS = 10;

	private static final int CANT_CARROS = 20;
	
	ITiqueteDao tiqueteDao;
	
	@Autowired
	public VigilanteServiceImpl(ITiqueteDao tiqueteDao) {
		this.tiqueteDao = tiqueteDao;
	}
	
	@Override
	@Transactional(readOnly=true)
	public boolean validarDisponibilidad(TipoVehiculo tipoVehiculo) {
		int cantVehiculosParqueados = tiqueteDao.countByTipoVehiculoAndFechaSalida(tipoVehiculo);
		
		if(!((TipoVehiculo.MOTO == tipoVehiculo && cantVehiculosParqueados < CANT_MOTOS)
				|| (TipoVehiculo.CARRO == tipoVehiculo && cantVehiculosParqueados < CANT_CARROS))) {
			throw new VehiculoException("No hay cupos disponibles");
		}
		
		return true;
	}

	@Override
	@Transactional(readOnly=true)
	public boolean validarPlaca(String placa, Calendar calendar) {
		int dia = calendar.get(Calendar.DAY_OF_WEEK);
		
		if(null!=tiqueteDao.findVehiculoByPlaca(placa)) {
			throw new VehiculoException("Vehiculo se encuentra registrado");
		}

		if(PLACA_NO_PERMITIDA.equalsIgnoreCase(placa.substring(0, 1)) && !validarDia(dia)) {
				throw new VehiculoException("Vehiculo no permitido");
		}
		
		return true;
	}
	
	public boolean validarDia(int dia) {
		return dia == Calendar.SUNDAY || dia == Calendar.MONDAY;
	}

}
