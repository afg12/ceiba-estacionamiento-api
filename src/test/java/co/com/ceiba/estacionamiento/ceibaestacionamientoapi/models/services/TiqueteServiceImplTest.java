package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.services;

import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.dao.ITiqueteDao;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.Tiquete;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.testdatabuilder.TiqueteCarroDataBuilder;
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
	public void buscarVehiculoporPlacaTest() {
		// arrange
		String placa = "44474";
		TiqueteCarroDataBuilder tiqueteCarro = new TiqueteCarroDataBuilder();
		Tiquete tiquete = tiqueteCarro.build();
		
		Mockito.when(tiqueteRepository.findVehiculoByPlaca(tiquete.getPlaca())).thenReturn(tiquete);
		
		//act
	    Tiquete tiqueteFound = tiqueteService.buscarvehiculoPlaca(placa);
	    
	    // assert
	    Assert.assertEquals(placa, tiqueteFound.getPlaca());
	 }
	
	@Test
	public void obtenerTiquetesTest() {
		// arrange
		List<Tiquete> listTiquete = Arrays.asList(new Tiquete("LTY123", null, TipoVehiculo.CARRO, new Date(), null, 0.00));
		
		Mockito.when(tiqueteRepository.findAll()).thenReturn(listTiquete);
		
		//act
		List<Tiquete> listTiqueteFound = tiqueteService.listarTiquetes();
	    
	    // assert
	    Assert.assertNotNull(listTiqueteFound);
	 }

}
