package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.services;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.exceptions.VehiculoException;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.Tiquete;

@Service
public class VigilanteServiceImpl implements IVigilanteService{
	
	private static final String PLACA_NO_PERMITIDA = "A";

	private static final String CARRO = "carro";
	
	private static final String MOTO = "moto";
	
	private static final int CANT_MOTOS = 10;

	private static final int CANT_CARROS = 20;
	
	
	@Autowired
	ITiqueteService tiqueteService;
	
	@Override
	@Transactional(readOnly=true)
	public boolean validarDisponibilidad(Tiquete tiquete) {
		long cantVehiculosParqueados = tiqueteService.cantParqueaderosDisponibles(tiquete.getTipoVehiculo());
		
		return ((MOTO.equalsIgnoreCase(tiquete.getTipoVehiculo()) && cantVehiculosParqueados < CANT_MOTOS)
				|| (CARRO.equalsIgnoreCase(tiquete.getTipoVehiculo()) && cantVehiculosParqueados < CANT_CARROS));
	}

	@Override
	@Transactional(readOnly=true)
	public boolean validarPlaca(String placa) {
		Calendar cal = Calendar.getInstance();
		int dia = cal.get(Calendar.DAY_OF_WEEK);
		
		if(null!=tiqueteService.validarVehiculo(placa)) {
			throw new VehiculoException("Vehiculo se encuentra registrado");
		}
		
		if(PLACA_NO_PERMITIDA.equalsIgnoreCase(placa.substring(0, 1)) && (dia == Calendar.SUNDAY || dia == Calendar.MONDAY)) {
			throw new VehiculoException("Vehiculo no permitido");
		}
		
		return true;
	}

}
