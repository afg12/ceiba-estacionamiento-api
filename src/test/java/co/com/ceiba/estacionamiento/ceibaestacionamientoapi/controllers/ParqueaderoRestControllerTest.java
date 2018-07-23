package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.CeibaEstacionamientoApiApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CeibaEstacionamientoApiApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class ParqueaderoRestControllerTest {
	
	//public static final String REST_SERVICE_URI = "http://localhost:8080/parqueadero";
	//private RestTemplate restTemplate;
	
	@Autowired
	private MockMvc mockMvc;
	
	//@Autowired
    //private WebApplicationContext webApplicationContext;
	
	 @Before
    public void setUp() {
        //restTemplate = new RestTemplate();
		 //this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
	 
	@After
    public void tearDown() {
        //restTemplate = null;
    }
	
	@Test
	public void verifyAllToDoList() throws Exception {
		mockMvc.perform(get("/parqueadero/listar").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}

	/*POST
	@Test
	public void isRegistrarEntradaTest() {
		//arrange
        HttpEntity<Tiquete> request = new HttpEntity<>(new Tiquete("L123", "400", TipoVehiculo.MOTO, new Date(), null, 2000.00));
        
        //act
        ResponseEntity<Tiquete> response = restTemplate.exchange("/registrar", HttpMethod.POST, request, Tiquete.class);
        
        //assert
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(HttpStatus.CREATED.value(),response.getStatusCode().value());
	}
	
	/*POST
	/*@Test
	public void noRegistrarEntradaTest() {
		//arrange
        HttpEntity<Tiquete> request = new HttpEntity<>(new Tiquete("L123", "400", TipoVehiculo.MOTO, new Date(), null, 2000.00));
    	
        try {
        	//act
        	restTemplate.exchange("/registrar", HttpMethod.POST, request, Tiquete.class);
        	fail();
        } catch (HttpClientErrorException e) {
        	//assert
        	Assert.assertEquals(HttpStatus.NOT_ACCEPTABLE, e.getStatusCode());
        }
	}
	
	/*PUT
	@Test
	public void facturarTest() {  	
        //act
        ResponseEntity<Tiquete> response = restTemplate.exchange("/facturar/1", HttpMethod.PUT, null, Tiquete.class);
        
    	//assert
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(HttpStatus.OK.value(),response.getStatusCode().value());
	}
	
	/*GET
	@Test
	public void listarTiquetesTest() {
        //act
		ResponseEntity<List<Tiquete>> response = restTemplate.exchange("/listar", HttpMethod.GET, null, new ParameterizedTypeReference<List<Tiquete>>(){});
        
    	//assert
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(HttpStatus.OK.value(),response.getStatusCode().value());
	}*/
	
}
