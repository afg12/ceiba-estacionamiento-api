package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.controllers;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.CeibaEstacionamientoApiApplication;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.Tiquete;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.util.TipoVehiculo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CeibaEstacionamientoApiApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class ParqueaderoRestControllerTest {
	
	public static final String REST_SERVICE_URI = "http://localhost:8080/parqueadero";
	private RestTemplate restTemplate;
	
	 @Before
    public void setUp() {
        restTemplate = new RestTemplate();
    }
	 
	@After
    public void tearDown() {
        restTemplate = null;
    }

	/*POST*/
	@Test
	public void isRegistrarEntradaTest() {
		//arrange
        HttpEntity<Tiquete> request = new HttpEntity<>(new Tiquete("L123", "400", TipoVehiculo.MOTO, new Date(), null, 2000.00));
        
        //act
        ResponseEntity<Tiquete> response = restTemplate.exchange(REST_SERVICE_URI+"/registrar", HttpMethod.POST, request, Tiquete.class);
        
        //assert
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(HttpStatus.CREATED.value(),response.getStatusCode().value());
	}
	
	/*POST*/
	@Test
	public void noRegistrarEntradaTest() {
		//arrange
        HttpEntity<Tiquete> request = new HttpEntity<>(new Tiquete("L123", "400", TipoVehiculo.MOTO, new Date(), null, 2000.00));
    	
        try {
        	//act
        	restTemplate.exchange(REST_SERVICE_URI+"/registrar", HttpMethod.POST, request, Tiquete.class);
        	fail();
        } catch (HttpClientErrorException e) {
        	//assert
        	Assert.assertEquals(HttpStatus.NOT_ACCEPTABLE, e.getStatusCode());
        }
	}
	
	/*PUT*/
	@Test
	public void facturarTest() {  	
        //act
        ResponseEntity<Tiquete> response = restTemplate.exchange(REST_SERVICE_URI+"/facturar/1", HttpMethod.PUT, null, Tiquete.class);
        
    	//assert
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(HttpStatus.OK.value(),response.getStatusCode().value());
	}
	
	/*GET*/
	@Test
	public void listarTiquetesTest() {
        //act
		ResponseEntity<List<Tiquete>> response = restTemplate.exchange(REST_SERVICE_URI+"/listar", HttpMethod.GET, null, new ParameterizedTypeReference<List<Tiquete>>(){});
        
    	//assert
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(HttpStatus.OK.value(),response.getStatusCode().value());
	}
	
}
