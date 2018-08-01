package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.dominio;

import java.util.Calendar;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.RegistroVehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.util.Constantes;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.util.TipoVehiculo;

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

	public double calcularCosto(RegistroVehiculo registro) {
		int total = 0;
		int valorHora = TipoVehiculo.CARRO == registro.getTipoVehiculo() ? Constantes.VALOR_HORA_CARRO: Constantes.VALOR_HORA_MOTO;
		int valorDia = TipoVehiculo.CARRO == registro.getTipoVehiculo()? Constantes.VALOR_DIA_CARRO : Constantes.VALOR_DIA_MOTO;
		
		Calendar calFechaInicial=Calendar.getInstance();
		Calendar calFechaFinal=Calendar.getInstance();
		calFechaInicial.setTime(registro.getFechaIngreso());
		
		int horasParqueo = CalculadoraHoras.getInstance().cantidadTotalHoras(calFechaInicial, calFechaFinal);
		int diasParqueo = horasParqueo/HORAS_DIA; 
		int horasResiduo = horasParqueo % HORAS_DIA;
		
		if(horasResiduo == 0) {
			total = valorHora;
		}
		
		if(horasResiduo >= Constantes.HORAS_MINIMAS) {
			diasParqueo +=1;
		} else {
			total += (horasResiduo * valorHora);
		}
		
		if(diasParqueo > 0) {
			total += (diasParqueo * valorDia);
		}
		
		if(TipoVehiculo.MOTO == registro.getTipoVehiculo() && Integer.parseInt(registro.getCilindraje()) > Constantes.CILINDRAJE_MINIMO) {
			total += Constantes.VALOR_ADICIONAL_MOTO; 
		}
		
		return (double) total;
	}

}
