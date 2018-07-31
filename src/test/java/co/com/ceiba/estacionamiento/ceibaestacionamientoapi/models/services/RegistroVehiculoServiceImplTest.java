package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.services;

import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.dao.IRegistroVehiculoDao;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.RegistroVehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.testdatabuilder.RegistroCarroDataBuilder;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.testdatabuilder.RegistroMotoDataBuilder;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.util.TipoVehiculo;

public class RegistroVehiculoServiceImplTest {

    private RegistroVehiculoServiceImpl tiqueteService;
 
    @Mock
    private IRegistroVehiculoDao registroRepository;
    
    @Before
    public void setUp() {
		registroRepository = mock(IRegistroVehiculoDao.class);
		tiqueteService = new RegistroVehiculoServiceImpl(registroRepository);
    }
	
	@Test
	public void cantidadVehiculosParqueadosTest() {
		// arrange
		TipoVehiculo tipoVehiculo = TipoVehiculo.MOTO;
		RegistroMotoDataBuilder tiqueteMoto = new RegistroMotoDataBuilder();
		RegistroVehiculo tiquete = tiqueteMoto.build();
		
		Mockito.when(registroRepository.countByTipoVehiculoAndFechaSalida(tiquete.getTipoVehiculo())).thenReturn(1);
		
		//act
	    int cantVehiculos = tiqueteService.cantParqueaderosDisponibles(tipoVehiculo);
	    
	    // assert
	    Assert.assertEquals(1, cantVehiculos);
	 }
	
	@Test
	public void buscarVehiculoporPlacaTest() {
		// arrange
		String placa = "44474";
		RegistroCarroDataBuilder tiqueteCarro = new RegistroCarroDataBuilder();
		RegistroVehiculo tiquete = tiqueteCarro.build();
		
		Mockito.when(registroRepository.findVehiculoByPlaca(tiquete.getPlaca())).thenReturn(tiquete);
		
		//act
	    RegistroVehiculo tiqueteFound = tiqueteService.buscarvehiculoPlaca(placa);
	    
	    // assert
	    Assert.assertEquals(placa, tiqueteFound.getPlaca());
	 }
	
	@Test
	public void obtenerTiquetesTest() {
		// arrange
		RegistroCarroDataBuilder tiqueteCarro = new RegistroCarroDataBuilder();
		RegistroVehiculo tiquete = tiqueteCarro.build();
		List<RegistroVehiculo> listTiquete = Arrays.asList(tiquete);
		
		Mockito.when(registroRepository.findAll()).thenReturn(listTiquete);
		
		//act
		List<RegistroVehiculo> listTiqueteFound = tiqueteService.listarTiquetes();
	    
	    // assert
	    Assert.assertNotNull(listTiqueteFound);
	 }
	
	@Test
	public void obtenerTiqueteporIdTest() {
		// arrange
		RegistroCarroDataBuilder tiqueteCarro = new RegistroCarroDataBuilder();
		RegistroVehiculo tiquete = tiqueteCarro.build();
		
		Mockito.doReturn(tiquete).when(registroRepository).findVehiculoById(Mockito.anyLong());
		
		//act
		RegistroVehiculo tiqueteFound = tiqueteService.buscarVehiculoId(1L);
	    
	    // assert
	    Assert.assertNotNull(tiqueteFound);
	 }

}
