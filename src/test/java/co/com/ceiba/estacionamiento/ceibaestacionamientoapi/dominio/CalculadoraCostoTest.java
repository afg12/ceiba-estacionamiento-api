package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.dominio;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.RegistroVehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.testdatabuilder.RegistroCarroDataBuilder;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.testdatabuilder.RegistroConCilindrajeMinimoBuilder;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.testdatabuilder.RegistroConHorasExtrasBuilder;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.testdatabuilder.RegistroMotoDataBuilder;

public class CalculadoraCostoTest {
	
	private CalculadoraCosto calculadoraCosto = CalculadoraCosto.getInstance();

	@Test
	public void calcularCostoMenorHorasMinimas() {
		// arrange
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.HOUR, -3);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		RegistroVehiculo tiquete = new RegistroMotoDataBuilder().setFechaI(calendar.getTime()).build();
		
		//act
		Double valorPagar = calculadoraCosto.calcularCosto(tiquete);
	    
	    // assert
	    Assert.assertEquals(tiquete.getTotal(), valorPagar);
	 }
	
	@Test
	public void calcularCostoMayorHorasMinimas() {
		// arrange
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.HOUR, -9);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		RegistroVehiculo tiquete = new RegistroCarroDataBuilder().setFechaI(calendar.getTime()).build();
		
		//act
		Double valorPagar = calculadoraCosto.calcularCosto(tiquete);
	    
	    // assert
	    Assert.assertEquals(tiquete.getTotal(), valorPagar);
	 }
	
	@Test
	public void calcularCostoMayorCilindrajeMinimo() {
		// arrange
		RegistroConCilindrajeMinimoBuilder tiqueteMoto = new RegistroConCilindrajeMinimoBuilder();
		RegistroVehiculo tiquete = tiqueteMoto.build();
		
		//act
		Double valorPagar = calculadoraCosto.calcularCosto(tiquete);
	    
	    // assert
	    Assert.assertEquals(tiquete.getTotal(), valorPagar);
	 }
	
	@Test
	public void calcularCostoHoras() {
		// arrange
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.HOUR, -27);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		RegistroVehiculo tiquete = new RegistroConHorasExtrasBuilder().setFechaI(calendar.getTime()).build();
		
		//act
		Double valorPagar = calculadoraCosto.calcularCosto(tiquete);
	    
	    // assert
	    Assert.assertEquals(tiquete.getTotal(), valorPagar);
	 }

}
