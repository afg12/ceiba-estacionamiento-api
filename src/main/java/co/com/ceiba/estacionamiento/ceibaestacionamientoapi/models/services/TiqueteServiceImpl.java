package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.services;


import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.dao.ITiqueteDao;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.Tiquete;

@Service
public class TiqueteServiceImpl implements ITiqueteService{
	

	private static final int VALOR_ADICIONAL_HORAS = 11000;

	private static final int VALOR_ADICIONAL_MOTO = 2000;

	private static final int CILINDRAJE_MINIMO = 500;

	private static final int HORAS_MINIMAS = 9;

	private static final int VALOR_HORA_MOTO = 500;

	private static final int VALOR_HORA_CARRO = 1000;
	
	private static final int VALOR_DIA_MOTO = 600;

	private static final int VALOR_DIA_CARRO = 8000;

	private static final String CARRO = "carro";

	@Autowired
	private ITiqueteDao tiqueteDao;
	
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
		
		int valorHora = VALOR_HORA_MOTO;
		int valorDia = VALOR_DIA_MOTO;
		
		if(CARRO.equalsIgnoreCase(tiquete.getTipoVehiculo())) {
			valorHora = VALOR_HORA_CARRO;
			valorDia = VALOR_DIA_CARRO;
		}
		
		Calendar calFechaInicial=Calendar.getInstance();
		Calendar calFechaFinal=Calendar.getInstance();
		Double total = null;

		calFechaInicial.setTime(tiquete.getFechaIngreso());
		
		int horasParqueo = cantidadTotalHoras(calFechaInicial, calFechaFinal);
		int diasParqueo = horasParqueo/24; 
		int horasResiduo = horasParqueo % 24;
		
		if(horasResiduo > HORAS_MINIMAS) {
			diasParqueo +=1;
		} else {
			total = (double) (horasResiduo * valorHora);
		}
		
		if(diasParqueo > 0) {
			total = total + (double) (diasParqueo * valorDia);
		}
		
		if(!tiquete.getCilindraje().isEmpty() && Integer.parseInt(tiquete.getCilindraje()) > CILINDRAJE_MINIMO) {
			total = total + VALOR_ADICIONAL_MOTO; 
		}
		
		if(horasParqueo == 27 ) {
			total = total + VALOR_ADICIONAL_HORAS;
		}
		
		return total;
	}

	private static int cantidadTotalHoras(Calendar fechaInicial, Calendar fechaFinal){
		int diferenciaHoras=0;
		diferenciaHoras=(int) ((fechaFinal.getTimeInMillis()-fechaInicial.getTimeInMillis())/1000/60/60);
		return diferenciaHoras;
	}

	@Override
	@Transactional(readOnly=true)
	public long cantParqueaderosDisponibles(String tipoVehiculo) {
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
