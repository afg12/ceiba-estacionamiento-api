package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.services;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.exceptions.CamposObligatoriosException;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.exceptions.VehiculoException;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.dao.IRegistroVehiculoDao;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.RegistroVehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.testdatabuilder.RegistroCarroDataBuilder;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.util.TipoVehiculo;

public class ParqueaderoServiceImplTest {
	
	private ParqueaderoServiceImpl parqueaderoService;
	 
    @Mock
    private IRegistroVehiculoDao registroRepository;
    
    @Before
    public void setUp() {
    	registroRepository = mock(IRegistroVehiculoDao.class);
		parqueaderoService = new ParqueaderoServiceImpl(registroRepository);
    }


	@Test
	public void isNoDisponibleParqueoCarro() {
		// arrange
		Mockito.when(registroRepository.countByTipoVehiculoAndFechaSalida(TipoVehiculo.CARRO)).thenReturn(20);
	    
	    //act
  		try {			
  			parqueaderoService.validarDisponibilidad(TipoVehiculo.CARRO);
  			fail();
  		} catch (VehiculoException e) {
  			// assert
  			Assert.assertEquals("No hay cupos disponibles", e.getMessage());
  		}
	}
	
	@Test
	public void isNoDisponibleParqueoMoto() {
		// arrange
		Mockito.when(registroRepository.countByTipoVehiculoAndFechaSalida(TipoVehiculo.MOTO)).thenReturn(10);
	    
	    //act
		try {			
			parqueaderoService.validarDisponibilidad(TipoVehiculo.MOTO);
			fail();
		} catch (VehiculoException e) {
			// assert
			Assert.assertEquals("No hay cupos disponibles", e.getMessage());
		}
	}
	
	
	@Test
	public void validarPlacaConA() {
		// arrange
		String placa ="AWD345";
		Calendar calendar =  Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		
		RegistroVehiculo tiquete = new RegistroCarroDataBuilder().setPlaca(placa).build();

	    //act
		try {			
			parqueaderoService.validarPlaca(tiquete.getPlaca(), calendar);
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
		
		RegistroVehiculo tiquete = new RegistroCarroDataBuilder().setPlaca(placa).build();
		Mockito.when(registroRepository.findVehiculoByPlaca(placa)).thenReturn(tiquete);

	    //act
		try {			
			parqueaderoService.validarPlaca(placa, calendar);
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
		boolean isvalidarDia = parqueaderoService.validarDia(calendar.get(Calendar.DAY_OF_WEEK));

		Assert.assertTrue(isvalidarDia);
	}
	
	@Test
	public void validarDiferenteDia() {
		// arrange
		Calendar calendar =  Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		
		//act
		boolean isvalidarDia = parqueaderoService.validarDia(calendar.get(Calendar.DAY_OF_WEEK));

		Assert.assertFalse(isvalidarDia);
	}
	
	@Test
	public void validarTipoVehiculoObligatorio() {
		// arrange
		TipoVehiculo tipoVehiculo = null;
		
		try {
			//act
			parqueaderoService.validarDisponibilidad(tipoVehiculo);
			fail();
		} catch(CamposObligatoriosException e) {	
			Assert.assertEquals("Los campos con * son obligatorios", e.getMessage());
		}
	}
	
	@Test
	public void validarPlacaObligatorio() {
		// arrange
		String placa = "";
		Calendar calendar =  Calendar.getInstance();
		
		try {
			//act
			parqueaderoService.validarPlaca(placa, calendar);
			fail();
		} catch(CamposObligatoriosException e) {	
			Assert.assertEquals("Los campos con * son obligatorios", e.getMessage());
		}
	}
	
}
