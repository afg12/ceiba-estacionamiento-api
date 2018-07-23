package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.testdatabuilder;

import java.util.Calendar;
import java.util.Date;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.Tiquete;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.util.TipoVehiculo;

public class TiqueteMotoDataBuilder {

	private static final String PLACA = "A426";
	private static final String CILINDRAJE = "500";
	private static final TipoVehiculo TIPO_VEHICULO = TipoVehiculo.MOTO;
	private static final Date FECHA_INGRESO = Calendar.getInstance().getTime();
	private static final Date FECHA_SALIDA = null;
	private static final Double TOTAL = 1500.00;
	
	private String placa;	
	private String cilindraje;
	private TipoVehiculo tipoVehiculo;
	private Date fechaIngreso; 
	private Date fechaSalida;
	private Double total;
	
	public TiqueteMotoDataBuilder() {
		this.placa = PLACA;
		this.cilindraje = CILINDRAJE;
		this.tipoVehiculo = TIPO_VEHICULO;
		this.fechaIngreso = FECHA_INGRESO;
		this.fechaSalida = FECHA_SALIDA;
		this.total = TOTAL;
	}

	public TiqueteMotoDataBuilder setPlaca(String placa) {
		this.placa = placa;
		return this;
	}
	
	public TiqueteMotoDataBuilder setCilindraje(String cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}
	
	public TiqueteMotoDataBuilder setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
		return this;
	}
	
	public TiqueteMotoDataBuilder setFechaI(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
		return this;
	}
	
	public TiqueteMotoDataBuilder setFechaS(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
		return this;
	}
	
	public TiqueteMotoDataBuilder setTotal(Double total) {
		this.total = total;
		return this;
	}
	
	public Tiquete build() {
		return new Tiquete(this.placa, this.cilindraje, this.tipoVehiculo, this.fechaIngreso, this.fechaSalida, this.total);
	}

}
