package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.dominio;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;

public class CalculadoraHorasTest {

	@Test
	public void cantidadTotalHorasTest() {
		// arrange
		Calendar fechaInicial = Calendar.getInstance();
		fechaInicial.add(Calendar.HOUR, -3);
		fechaInicial.set(Calendar.MINUTE, 0);
		fechaInicial.set(Calendar.SECOND, 0);
		fechaInicial.set(Calendar.MILLISECOND, 0);
		
		//act
		int cantidadHoras = CalculadoraHoras.getInstance().cantidadTotalHoras(fechaInicial, Calendar.getInstance());
		
		//assert
		assertEquals(3, cantidadHoras);
	}

}
