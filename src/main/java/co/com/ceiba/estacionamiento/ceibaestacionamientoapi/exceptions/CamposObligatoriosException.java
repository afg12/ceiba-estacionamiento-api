package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CamposObligatoriosException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CamposObligatoriosException(String message) {
		super(message);
	}
}
