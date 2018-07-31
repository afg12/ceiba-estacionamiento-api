package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.testdatabuilder;

import java.util.Calendar;
import java.util.Date;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.RegistroVehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.util.TipoVehiculo;

public class RegistroConCilindrajeMinimoBuilder {
	
	private static final String PLACA = "A426";
	private static final String CILINDRAJE = "1000";
	private static final TipoVehiculo TIPO_VEHICULO = TipoVehiculo.MOTO;
	private static final Date FECHA_INGRESO = Calendar.getInstance().getTime();
	private static final Date FECHA_SALIDA = null;
	private static final Double TOTAL = 2500.00;
	
	private String placa;	
	private String cilindraje;
	private TipoVehiculo tipoVehiculo;
	private Date fechaIngreso; 
	private Date fechaSalida;
	private Double total;
	
	public RegistroConCilindrajeMinimoBuilder() {
		this.placa = PLACA;
		this.cilindraje = CILINDRAJE;
		this.tipoVehiculo = TIPO_VEHICULO;
		this.fechaIngreso = FECHA_INGRESO;
		this.fechaSalida = FECHA_SALIDA;
		this.total = TOTAL;
	}

	public RegistroConCilindrajeMinimoBuilder setPlaca(String placa) {
		this.placa = placa;
		return this;
	}
	
	public RegistroConCilindrajeMinimoBuilder setCilindraje(String cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}
	
	public RegistroConCilindrajeMinimoBuilder setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
		return this;
	}
	
	public RegistroConCilindrajeMinimoBuilder setFechaI(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
		return this;
	}
	
	public RegistroConCilindrajeMinimoBuilder setFechaS(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
		return this;
	}
	
	public RegistroConCilindrajeMinimoBuilder setTotal(Double total) {
		this.total = total;
		return this;
	}
	
	public RegistroVehiculo build() {
		return new RegistroVehiculo(this.placa, this.cilindraje, this.tipoVehiculo, this.fechaIngreso, this.fechaSalida, this.total);
	}

}
