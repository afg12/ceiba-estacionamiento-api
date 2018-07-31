package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.dominio;

import java.util.Calendar;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.Tiquete;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.util.TipoVehiculo;

import static co.com.ceiba.estacionamiento.ceibaestacionamientoapi.util.Constantes.*;

public final class CalculadoraCosto {
	
	private static final int HORAS_DIA = 24;
	
	private static CalculadoraCosto calculadoraCosto;
	
	private CalculadoraCosto() {
		
	}
	
	public static CalculadoraCosto getInstance() {
		if(null == calculadoraCosto) {
			calculadoraCosto = new CalculadoraCosto();
		}
		return calculadoraCosto;
	}

	public double calcularCosto(Tiquete tiquete) {
		int total = 0;
		int valorHora = TipoVehiculo.CARRO == tiquete.getTipoVehiculo() ? VALOR_HORA_CARRO: VALOR_HORA_MOTO;
		int valorDia = TipoVehiculo.CARRO == tiquete.getTipoVehiculo()? VALOR_DIA_CARRO : VALOR_DIA_MOTO;
		
		Calendar calFechaInicial=Calendar.getInstance();
		Calendar calFechaFinal=Calendar.getInstance();
		calFechaInicial.setTime(tiquete.getFechaIngreso());
		
		int horasParqueo = CalculadoraHoras.getInstance().cantidadTotalHoras(calFechaInicial, calFechaFinal);
		int diasParqueo = horasParqueo/HORAS_DIA; 
		int horasResiduo = horasParqueo % HORAS_DIA;
		
		if(horasParqueo == 0) {
			total = valorHora;
		}
		
		if(horasResiduo >= HORAS_MINIMAS) {
			diasParqueo +=1;
		} else {
			total += (horasResiduo * valorHora);
		}
		
		if(diasParqueo > 0) {
			total += (diasParqueo * valorDia);
		}
		
		if(TipoVehiculo.MOTO == tiquete.getTipoVehiculo() && Integer.parseInt(tiquete.getCilindraje()) > CILINDRAJE_MINIMO) {
			total += VALOR_ADICIONAL_MOTO; 
		}
		
		return (double) total;
	}

}
