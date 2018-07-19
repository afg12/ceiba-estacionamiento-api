package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.builder;

import java.util.Calendar;
import java.util.Date;

import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity.Tiquete;
import co.com.ceiba.estacionamiento.ceibaestacionamientoapi.util.TipoVehiculo;

public class TiqueteCarroDataBuilderTest {
	private static final String PLACA = "44474";
	private static final String CILINDRAJE = null;
	private static final TipoVehiculo TIPO_VEHICULO = TipoVehiculo.CARRO;
	private static final Date FECHA_INGRESO = Calendar.getInstance().getTime();
	private static final Date FECHA_SALIDA = null;
	private static final Double TOTAL = 8000.00;
	
	private String placa;	
	private String cilindraje;
	private TipoVehiculo tipoVehiculo;
	private Date fechaIngreso; 
	private Date fechaSalida;
	private Double total;
	
	public TiqueteCarroDataBuilderTest() {
		this.placa = PLACA;
		this.cilindraje = CILINDRAJE;
		this.tipoVehiculo = TIPO_VEHICULO;
		this.fechaIngreso = FECHA_INGRESO;
		this.fechaSalida = FECHA_SALIDA;
		this.total = TOTAL;
	}
	
	public TiqueteCarroDataBuilderTest setPlaca(String placa) {
		this.placa = placa;
		return this;
	}
	
	public TiqueteCarroDataBuilderTest setCilindraje(String cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}
	
	public TiqueteCarroDataBuilderTest setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
		return this;
	}
	
	public TiqueteCarroDataBuilderTest setFechaI(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
		return this;
	}
	
	public TiqueteCarroDataBuilderTest setFechaS(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
		return this;
	}
	
	public TiqueteCarroDataBuilderTest setTotal(Double total) {
		this.total = total;
		return this;
	}
	
	public Tiquete build() {
		return new Tiquete(this.placa, this.cilindraje, this.tipoVehiculo, this.fechaIngreso, this.fechaSalida, this.total);
	}
}
