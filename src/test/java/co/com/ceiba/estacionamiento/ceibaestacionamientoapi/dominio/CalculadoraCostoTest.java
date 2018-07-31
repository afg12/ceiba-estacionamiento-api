package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.dominio;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.Tiquete;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.testdatabuilder.TiqueteCarroDataBuilder;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.testdatabuilder.TiqueteCilindrajeMinimoBuilder;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.testdatabuilder.TiqueteHorasExtrasBuilder;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.testdatabuilder.TiqueteMotoDataBuilder;

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
		
		Tiquete tiquete = new TiqueteMotoDataBuilder().setFechaI(calendar.getTime()).build();
		
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
		
		Tiquete tiquete = new TiqueteCarroDataBuilder().setFechaI(calendar.getTime()).build();
		
		//act
		Double valorPagar = calculadoraCosto.calcularCosto(tiquete);
	    
	    // assert
	    Assert.assertEquals(tiquete.getTotal(), valorPagar);
	 }
	
	@Test
	public void calcularCostoMayorCilindrajeMinimo() {
		// arrange
		TiqueteCilindrajeMinimoBuilder tiqueteMoto = new TiqueteCilindrajeMinimoBuilder();
		Tiquete tiquete = tiqueteMoto.build();
		
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
		
		Tiquete tiquete = new TiqueteHorasExtrasBuilder().setFechaI(calendar.getTime()).build();
		
		//act
		Double valorPagar = calculadoraCosto.calcularCosto(tiquete);
	    
	    // assert
	    Assert.assertEquals(tiquete.getTotal(), valorPagar);
	 }

}
