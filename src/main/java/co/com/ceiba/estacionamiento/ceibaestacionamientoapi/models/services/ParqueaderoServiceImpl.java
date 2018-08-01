package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.services;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.exceptions.CamposObligatoriosException;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.exceptions.VehiculoException;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.dao.IRegistroVehiculoDao;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.RegistroVehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.util.Constantes;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.util.TipoVehiculo;

@Service("parqueaderoService")
public class ParqueaderoServiceImpl implements IParqueaderoService{
	
	IRegistroVehiculoDao registroVehiculoDao;
	
	@Autowired
	public ParqueaderoServiceImpl(IRegistroVehiculoDao registroVehiculoDao) {
		this.registroVehiculoDao = registroVehiculoDao;
	}
	
	@Override
	@Transactional(readOnly=true)
	public void validarDisponibilidad(TipoVehiculo tipoVehiculo) {
		if(null == tipoVehiculo) {
			throw new CamposObligatoriosException("Los campos con * son obligatorios");
		}
		
		int cantVehiculosParqueados = registroVehiculoDao.countByTipoVehiculoAndFechaSalida(tipoVehiculo);
		
		Celda celda = FactoryCelda.getCelda(tipoVehiculo);
		
		if( cantVehiculosParqueados == celda.getMaximoCelda() ) {
			throw new VehiculoException("No hay cupos disponibles");
		}
	}

	@Override
	@Transactional(readOnly=true)
	public void validarPlaca(String placa, Calendar calendar) {
		if(placa.isEmpty()) {
			throw new CamposObligatoriosException("Los campos con * son obligatorios");
		}
		
		int dia = calendar.get(Calendar.DAY_OF_WEEK);
		
		if(null!=registroVehiculoDao.findVehiculoByPlacaAndFechaSalidaNull(placa)) {
			throw new VehiculoException("Vehiculo se encuentra registrado");
		}

		if(Constantes.PLACA_NO_PERMITIDA.equalsIgnoreCase(placa.substring(0, 1)) && !validarDia(dia)) {
			throw new VehiculoException("Vehiculo no permitido");
		}
	}
	
	public boolean validarDia(int dia) {
		return dia == Calendar.SUNDAY || dia == Calendar.MONDAY;
	}

}
