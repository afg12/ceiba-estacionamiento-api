package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.services;

import static org.mockito.Mockito.mock;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.dao.ITiqueteDao;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.Tiquete;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.testdatabuilder.TiqueteCarroDataBuilder;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.testdatabuilder.TiqueteCilindrajeMinimoBuilder;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.testdatabuilder.TiqueteHorasExtrasBuilder;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.testdatabuilder.TiqueteMotoDataBuilder;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.util.TipoVehiculo;

public class TiqueteServiceImplTest {

    private TiqueteServiceImpl tiqueteService;
 
    @Mock
    private ITiqueteDao tiqueteRepository;
    
    @Before
    public void setUp() {
		tiqueteRepository = mock(ITiqueteDao.class);
		tiqueteService = new TiqueteServiceImpl(tiqueteRepository);
    }
	
	@Test
	public void cantidadVehiculosParqueadosTest() {
		// arrange
		TipoVehiculo tipoVehiculo = TipoVehiculo.MOTO;
		TiqueteMotoDataBuilder tiqueteMoto = new TiqueteMotoDataBuilder();
		Tiquete tiquete = tiqueteMoto.build();
		
		Mockito.when(tiqueteRepository.countByTipoVehiculoAndFechaSalida(tiquete.getTipoVehiculo())).thenReturn(1);
		
		//act
	    int cantVehiculos = tiqueteService.cantParqueaderosDisponibles(tipoVehiculo);
	    
	    // assert
	    Assert.assertEquals(1, cantVehiculos);
	 }
	
	@Test
	public void validarVehiculoTest() {
		// arrange
		String placa = "44474";
		TiqueteCarroDataBuilder tiqueteCarro = new TiqueteCarroDataBuilder();
		Tiquete tiquete = tiqueteCarro.build();
		
		Mockito.when(tiqueteRepository.findVehiculoByPlaca(tiquete.getPlaca())).thenReturn(tiquete);
		
		//act
	    Tiquete tiqueteFound = tiqueteService.validarVehiculo(placa);
	    
	    // assert
	    Assert.assertEquals(placa, tiqueteFound.getPlaca());
	 }
	
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
		Mockito.when(tiqueteRepository.save(tiquete)).thenReturn(tiquete);
		
		//act
		Double valorPagar = tiqueteService.calcularCosto(tiquete);
	    
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
		Mockito.when(tiqueteRepository.save(tiquete)).thenReturn(tiquete);
		
		//act
		Double valorPagar = tiqueteService.calcularCosto(tiquete);
	    
	    // assert
	    Assert.assertEquals(tiquete.getTotal(), valorPagar);
	 }
	
	@Test
	public void calcularCostoMayorCilindrajeMinimo() {
		// arrange
		TiqueteCilindrajeMinimoBuilder tiqueteMoto = new TiqueteCilindrajeMinimoBuilder();
		Tiquete tiquete = tiqueteMoto.build();
		Mockito.when(tiqueteRepository.save(tiquete)).thenReturn(tiquete);
		
		//act
		Double valorPagar = tiqueteService.calcularCosto(tiquete);
	    
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
		Mockito.when(tiqueteRepository.save(tiquete)).thenReturn(tiquete);
		
		//act
		Double valorPagar = tiqueteService.calcularCosto(tiquete);
	    
	    // assert
	    Assert.assertEquals(tiquete.getTotal(), valorPagar);
	 }

}
