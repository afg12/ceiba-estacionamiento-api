package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.dominio;

import java.util.Calendar;

public final class CalculadoraHoras {
	
	private static CalculadoraHoras calculadoraHoras;
	
	private CalculadoraHoras() {
		
	}
	
	public static CalculadoraHoras getInstance() {
		if(null == calculadoraHoras) {
			calculadoraHoras = new CalculadoraHoras();
		}
		return calculadoraHoras;
	}
	
	public int cantidadTotalHoras(Calendar fechaInicial, Calendar fechaFinal){
		int diferenciaHoras=0;
		diferenciaHoras=(int) ((fechaFinal.getTimeInMillis()-fechaInicial.getTimeInMillis())/1000/60/60);
		return diferenciaHoras;
	}

}
