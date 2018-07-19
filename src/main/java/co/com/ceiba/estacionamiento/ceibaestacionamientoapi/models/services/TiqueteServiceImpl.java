package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.services;


import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.dao.ITiqueteDao;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.Tiquete;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.util.TipoVehiculo;

@Service
public class TiqueteServiceImpl implements ITiqueteService{
	

	private static final int VALOR_ADICIONAL_MOTO = 2000;

	private static final int CILINDRAJE_MINIMO = 500;

	private static final int HORAS_MINIMAS = 9;

	private static final int VALOR_HORA_MOTO = 500;

	private static final int VALOR_HORA_CARRO = 1000;
	
	private static final int VALOR_DIA_MOTO = 600;

	private static final int VALOR_DIA_CARRO = 8000;

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
	public Double calcularCosto(Tiquete tiquete) {
		
		int total = 0;
		int valorHora = TipoVehiculo.CARRO == tiquete.getTipoVehiculo() ? VALOR_HORA_CARRO: VALOR_HORA_MOTO;
		int valorDia = TipoVehiculo.CARRO == tiquete.getTipoVehiculo()? VALOR_DIA_CARRO : VALOR_DIA_MOTO;
		
		Calendar calFechaInicial=Calendar.getInstance();
		Calendar calFechaFinal=Calendar.getInstance();
		calFechaInicial.setTime(tiquete.getFechaIngreso());
		
		int horasParqueo = cantidadTotalHoras(calFechaInicial, calFechaFinal);
		int diasParqueo = horasParqueo/24; 
		int horasResiduo = horasParqueo % 24;
		
		if(horasResiduo >= HORAS_MINIMAS) {
			diasParqueo +=1;
		} else {
			total = (horasResiduo * valorHora);
		}
		
		if(diasParqueo > 0) {
			total = total + (diasParqueo * valorDia);
		}
		
		if(TipoVehiculo.MOTO == tiquete.getTipoVehiculo() && Integer.parseInt(tiquete.getCilindraje()) > CILINDRAJE_MINIMO) {
			total = total + VALOR_ADICIONAL_MOTO; 
		}
		
		return (double) total;
	}

	private static int cantidadTotalHoras(Calendar fechaInicial, Calendar fechaFinal){
		int diferenciaHoras=0;
		diferenciaHoras=(int) ((fechaFinal.getTimeInMillis()-fechaInicial.getTimeInMillis())/1000/60/60);
		return diferenciaHoras;
	}

	@Override
	@Transactional(readOnly=true)
	public int cantParqueaderosDisponibles(TipoVehiculo tipoVehiculo) {
		return tiqueteDao.countByTipoVehiculoAndFechaSalida(tipoVehiculo);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Tiquete buscarVehiculoRegistrado(Long id) {
		return tiqueteDao.findVehiculoById(id);
	}

	@Override
	public Tiquete validarVehiculo(String placa) {
		return tiqueteDao.findVehiculoByPlaca(placa);
	}

}
