package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.services;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.exceptions.VehiculoException;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.dao.ITiqueteDao;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.Tiquete;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.testdatabuilder.TiqueteCarroDataBuilder;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.util.TipoVehiculo;

public class VigilanteServiceImplTest {
	
	private VigilanteServiceImpl vigilanteService;
	 
    @Mock
    private ITiqueteDao tiqueteRepository;
    
    @Before
    public void setUp() {
		tiqueteRepository = mock(ITiqueteDao.class);
		vigilanteService = new VigilanteServiceImpl(tiqueteRepository);
    }

	@Test
	public void isDisponibleParqueoMoto() {
		// arrange
		Mockito.when(tiqueteRepository.countByTipoVehiculoAndFechaSalida(TipoVehiculo.MOTO)).thenReturn(9);
		
		//act
	    boolean isDisponibleParqueo = vigilanteService.validarDisponibilidad(TipoVehiculo.MOTO);
	    
	    // assert
	    Assert.assertTrue(isDisponibleParqueo);
	}
	
	@Test
	public void isNoDisponibleParqueoCarro() {
		// arrange
		Mockito.when(tiqueteRepository.countByTipoVehiculoAndFechaSalida(TipoVehiculo.CARRO)).thenReturn(20);
	    
	    //act
  		try {			
  			vigilanteService.validarDisponibilidad(TipoVehiculo.CARRO);
  			fail();
  		} catch (VehiculoException e) {
  			// assert
  			Assert.assertEquals("No hay cupos disponibles", e.getMessage());
  		}
	}
	
	@Test
	public void isNoDisponibleParqueoMoto() {
		// arrange
		Mockito.when(tiqueteRepository.countByTipoVehiculoAndFechaSalida(TipoVehiculo.MOTO)).thenReturn(10);
	    
	    //act
		try {			
			vigilanteService.validarDisponibilidad(TipoVehiculo.MOTO);
			fail();
		} catch (VehiculoException e) {
			// assert
			Assert.assertEquals("No hay cupos disponibles", e.getMessage());
		}
	}
	
	@Test
	public void isPlacaPermitida() {
		// arrange
		Calendar calendar =  Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		
		TiqueteCarroDataBuilder tiqueteCarro = new TiqueteCarroDataBuilder();
		Tiquete tiquete = tiqueteCarro.build();
		
		//act
	    boolean isPlacaPermitida = vigilanteService.validarPlaca(tiquete.getPlaca(), calendar);
	    
	    // assert
	    Assert.assertTrue(isPlacaPermitida);
	}
	
	@Test
	public void validarPlacaConA() {
		// arrange
		String placa ="AWD345";
		Calendar calendar =  Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		
		Tiquete tiquete = new TiqueteCarroDataBuilder().setPlaca(placa).build();

	    //act
		try {			
			vigilanteService.validarPlaca(tiquete.getPlaca(), calendar);
			fail();
		} catch (VehiculoException e) {
			// assert
			Assert.assertEquals("Vehiculo no permitido", e.getMessage());
		}
	}
	
	@Test
	public void validarVehiculoRegistrado() {
		// arrange
		String placa ="GWD345";
		Calendar calendar =  Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		
		Tiquete tiquete = new TiqueteCarroDataBuilder().setPlaca(placa).build();
		Mockito.when(tiqueteRepository.findVehiculoByPlaca(placa)).thenReturn(tiquete);

	    //act
		try {			
			vigilanteService.validarPlaca(placa, calendar);
			fail();
		} catch (VehiculoException e) {
			// assert
			Assert.assertEquals("Vehiculo se encuentra registrado", e.getMessage());
		}
	}
	
	@Test
	public void validarDiaLunes() {
		// arrange
		Calendar calendar =  Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		
		//act
		boolean isvalidarDia = vigilanteService.validarDia(calendar.get(Calendar.DAY_OF_WEEK));

		Assert.assertTrue(isvalidarDia);
	}
	
	@Test
	public void validarDiferenteDia() {
		// arrange
		Calendar calendar =  Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		
		//act
		boolean isvalidarDia = vigilanteService.validarDia(calendar.get(Calendar.DAY_OF_WEEK));

		Assert.assertFalse(isvalidarDia);
	}
	
}
