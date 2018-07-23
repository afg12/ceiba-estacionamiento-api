package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class VehiculoException extends RuntimeException {
	
private static final long serialVersionUID = 1L;

	public VehiculoException() {
	}
	
	public VehiculoException(String message) {
		super(message);
	}

}
