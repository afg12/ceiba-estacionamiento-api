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

    private RegistroVehiculoServiceImpl registroVehiculoService;
 
    @Mock
    private IRegistroVehiculoDao registroRepository;
    
    @Before
    public void setUp() {
		registroRepository = mock(IRegistroVehiculoDao.class);
		registroVehiculoService = new RegistroVehiculoServiceImpl(registroRepository);
    }
	
	@Test
	public void cantidadVehiculosParqueadosTest() {
		// arrange
		TipoVehiculo tipoVehiculo = TipoVehiculo.MOTO;
		RegistroMotoDataBuilder registroMoto = new RegistroMotoDataBuilder();
		RegistroVehiculo registro = registroMoto.build();
		
		Mockito.when(registroRepository.countByTipoVehiculoAndFechaSalida(registro.getTipoVehiculo())).thenReturn(1);
		
		//act
	    int cantVehiculos = registroVehiculoService.cantParqueaderosDisponibles(tipoVehiculo);
	    
	    // assert
	    Assert.assertEquals(1, cantVehiculos);
	 }
	
	@Test
	public void buscarVehiculoporPlacaTest() {
		// arrange
		String placa = "44474";
		RegistroCarroDataBuilder registroCarro = new RegistroCarroDataBuilder();
		RegistroVehiculo registro = registroCarro.build();
		
		Mockito.when(registroRepository.findVehiculoByPlaca(placa)).thenReturn(registro);
		
		//act
	    RegistroVehiculo registroEncontrado = registroVehiculoService.buscarVehiculoPlaca(placa);
	    
	    // assert
	    Assert.assertEquals(placa, registroEncontrado.getPlaca());
	 }
	
	@Test
	public void obtenerRegistrosTest() {
		// arrange
		RegistroCarroDataBuilder registroCarro = new RegistroCarroDataBuilder();
		RegistroVehiculo registro = registroCarro.build();
		List<RegistroVehiculo> listRegistros = Arrays.asList(registro);
		
		Mockito.when(registroRepository.findAll()).thenReturn(listRegistros);
		
		//act
		List<RegistroVehiculo> listRegistrosEncontrados = registroVehiculoService.listarRegistros();
	    
	    // assert
	    Assert.assertNotNull(listRegistrosEncontrados);
	 }
	
	@Test
	public void obtenerRegistroIdTest() {
		// arrange
		RegistroCarroDataBuilder registroCarro = new RegistroCarroDataBuilder();
		RegistroVehiculo registro = registroCarro.build();
		
		Mockito.doReturn(registro).when(registroRepository).findVehiculoById(Mockito.anyLong());
		
		//act
		RegistroVehiculo registroEncontrado = registroVehiculoService.buscarVehiculoId(1L);
	    
	    // assert
	    Assert.assertNotNull(registroEncontrado);
	 }

}
