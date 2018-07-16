package co.com.ceiba.estacionamiento.ceibaestacionamientoapi.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

@Entity
public class Parqueadero implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String placa;
	private String cilindraje;
	
	@Column(name="tipo_vehiculo")
	private String tipoVehiculo;
	
	@Column(name="fecha_ingreso")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaIngreso;
	
	@Column(name="fecha_salida")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaSalida;
	
	private float total;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	
	public Parqueadero() {
		
	}
	
	public Parqueadero(Long id, @NotEmpty String placa, String cilindraje, String tipoVehiculo, Date fechaIngreso,
			Date fechaSalida, float total) {
		super();
		this.id = id;
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.tipoVehiculo = tipoVehiculo;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.total = total;
	}
	
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public Date getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getCilindraje() {
		return cilindraje;
	}
	public void setCilindraje(String cilindraje) {
		this.cilindraje = cilindraje;
	}
	public String getTipoVehiculo() {
		return tipoVehiculo;
	}
	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	
}
