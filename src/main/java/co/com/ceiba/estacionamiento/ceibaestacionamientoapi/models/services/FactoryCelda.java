package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.services;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.util.TipoVehiculo;

public final class FactoryCelda {
	
	private FactoryCelda() {
	    
	}
	
	public static Celda getCelda(TipoVehiculo tipoVehiculo ) {
		Celda celda = null;
		switch(tipoVehiculo) {
		case MOTO:
			celda = new CeldaMoto();
			break;
		case CARRO:
			celda = new CeldaCarro();
			break;
		default:
			break;
		}
		
		return celda;
	}

}
