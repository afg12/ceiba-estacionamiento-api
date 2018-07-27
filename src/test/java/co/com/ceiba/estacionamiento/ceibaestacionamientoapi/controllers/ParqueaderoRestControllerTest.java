package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.CeibaEstacionamientoApiApplication;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.Tiquete;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.services.TiqueteServiceImpl;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.util.TipoVehiculo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CeibaEstacionamientoApiApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class ParqueaderoRestControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
    private TiqueteServiceImpl tiqueteService;
	
	@Test
	public void tiquetesNoEncontradosTest() throws Exception {
		//arrange
		List<Tiquete> tiquetes = new ArrayList<>();
		when(tiqueteService.listarTiquetes()).thenReturn(tiquetes);
		
		//act
		MockHttpServletResponse response = mockMvc.perform(get("/parqueadero/listar").accept(MediaType.APPLICATION_JSON))
			.andDo(print()).andReturn().getResponse();
		
		//assert
		Assert.assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
	}
	
	@Test
	public void isRegistrarEntradaTest() throws Exception {
		
		MockHttpServletResponse response = mockMvc.perform(
                post("/parqueadero/registrar").contentType(MediaType.APPLICATION_JSON).content(
                		asJsonString(new Tiquete("JTY123", null, TipoVehiculo.CARRO, new Date(), null, 0.00))
                )).andReturn().getResponse();
		
		//assert
		Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}
	
	@Test
	public void listarTiquetesTest() throws Exception {
		//arrange
		List<Tiquete> tiquetes = Arrays.asList(new Tiquete("LTY123", null, TipoVehiculo.CARRO, new Date(), null, 0.00));
		when(tiqueteService.listarTiquetes()).thenReturn(tiquetes);
		
		//act
		MockHttpServletResponse response = mockMvc.perform(get("/parqueadero/listar").accept(MediaType.APPLICATION_JSON))
			.andDo(print()).andReturn().getResponse();
		
		//assert
		Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void facturarTest() throws Exception{
		//arrange
		Tiquete tiquete = new Tiquete("LTY123", null, TipoVehiculo.CARRO, new Date(), null, 0.00);
		when(tiqueteService.buscarVehiculoRegistrado(1L)).thenReturn(tiquete);
		
		MockHttpServletResponse response = mockMvc.perform(
                put("/parqueadero/facturar/{id}", 1L).contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(tiquete))).andDo(print()).andReturn().getResponse();

		//assert
		Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	private static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	} 
	
}
