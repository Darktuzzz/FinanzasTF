package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Letra")
public class Letra implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idLetra;
	
	@NotEmpty(message = "Debe ingresar un valor")
	@Column(name="valor_nominal", length=15, nullable=false)
	private String valor_nominal;
	
	@NotNull(message = "La fecha es obligatoria")
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_emision")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fecha_emision;
	
	@NotNull(message = "La fecha es obligatoria")
	@Future(message = "La fecha debe estar en el futuro")
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_vencimiento")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fecha_vencimiento;
	
	@Column(name="valorTasa", length=15, nullable=false)
	private double valorTasa;
	
	@ManyToOne
	@JoinColumn(name="tasa", nullable=false)
	private Tasa tasa;
	
	@ManyToOne
	@JoinColumn(name = "tipoTasa")
	private TipoTasa tipoTasa;
	
	@Column(name="costes_gastos", nullable=false)
	private double costes_gastos;
	
	@ManyToOne
	@JoinColumn(name = "idMoneda")
	private Moneda idMoneda;

	public Letra() {
		super();
	}

	public Letra(int idLetra, @NotEmpty(message = "Debe ingresar un valor") String valor_nominal,
			@NotNull(message = "La fecha es obligatoria") Date fecha_emision,
			@NotNull(message = "La fecha es obligatoria") @Future(message = "La fecha debe estar en el futuro") Date fecha_vencimiento,
			double valorTasa, Tasa tasa, TipoTasa tipoTasa, double costes_gastos, Moneda idMoneda) {
		super();
		this.idLetra = idLetra;
		this.valor_nominal = valor_nominal;
		this.fecha_emision = fecha_emision;
		this.fecha_vencimiento = fecha_vencimiento;
		this.valorTasa = valorTasa;
		this.tasa = tasa;
		this.tipoTasa = tipoTasa;
		this.costes_gastos = costes_gastos;
		this.idMoneda = idMoneda;
	}

	public int getIdLetra() {
		return idLetra;
	}

	public void setIdLetra(int idLetra) {
		this.idLetra = idLetra;
	}

	public String getValor_nominal() {
		return valor_nominal;
	}

	public void setValor_nominal(String valor_nominal) {
		this.valor_nominal = valor_nominal;
	}

	public Date getFecha_emision() {
		return fecha_emision;
	}

	public void setFecha_emision(Date fecha_emision) {
		this.fecha_emision = fecha_emision;
	}

	public Date getFecha_vencimiento() {
		return fecha_vencimiento;
	}

	public void setFecha_vencimiento(Date fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}

	public double getValorTasa() {
		return valorTasa;
	}

	public void setValorTasa(double valorTasa) {
		this.valorTasa = valorTasa;
	}

	public Tasa getTasa() {
		return tasa;
	}

	public void setTasa(Tasa tasa) {
		this.tasa = tasa;
	}

	public TipoTasa getTipoTasa() {
		return tipoTasa;
	}

	public void setTipoTasa(TipoTasa tipoTasa) {
		this.tipoTasa = tipoTasa;
	}

	public double getCostes_gastos() {
		return costes_gastos;
	}

	public void setCostes_gastos(double costes_gastos) {
		this.costes_gastos = costes_gastos;
	}

	public Moneda getIdMoneda() {
		return idMoneda;
	}

	public void setIdMoneda(Moneda idMoneda) {
		this.idMoneda = idMoneda;
	}

}
