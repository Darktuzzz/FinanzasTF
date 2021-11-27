package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Cartera")
public class Cartera implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCartera;

	@ManyToOne
	@JoinColumn(name = "idLetra", nullable = false)
	private Letra letra;
	
	@Column(name="tasaConvertida", length=8, nullable=true)
	private double tasaConvertida;
	
	@Column(name="tasaDescuento", length=8, nullable=true)
	private double tasaDescuento;
	
	@Column(name="descuento", length=20, nullable=true)
	private double descuento;
	
	@Column(name="valor_neto", length=20, nullable=true)
	private double valor_neto;
	
	@Column(name="valor_recibido", length=20, nullable=true)
	private double valor_recibido;
	
	@Column(name="valor_entregado", length=20, nullable=true)
	private double valor_entregado;
	
	@Column(name="plazo", length=8, nullable=true)
	private int plazo;
	
	@Column(name="TCEA", length=20, nullable=true)
	private double TCEA;
	
	public Cartera() {
		super();
	}

	public Cartera(int idCartera, Letra letra, double tasaConvertida, double tasaDescuento, double descuento,
			double valor_neto, double valor_recibido, double valor_entregado, int plazo, double tCEA) {
		super();
		this.idCartera = idCartera;
		this.letra = letra;
		this.tasaConvertida = tasaConvertida;
		this.tasaDescuento = tasaDescuento;
		this.descuento = descuento;
		this.valor_neto = valor_neto;
		this.valor_recibido = valor_recibido;
		this.valor_entregado = valor_entregado;
		this.plazo = plazo;
		TCEA = tCEA;
	}

	public int getIdCartera() {
		return idCartera;
	}

	public void setIdCartera(int idCartera) {
		this.idCartera = idCartera;
	}

	public Letra getLetra() {
		return letra;
	}

	public void setLetra(Letra letra) {
		this.letra = letra;
	}

	public double getTasaConvertida() {
		return tasaConvertida;
	}

	public void setTasaConvertida(double tasaConvertida) {
		this.tasaConvertida = tasaConvertida;
	}

	public double getTasaDescuento() {
		return tasaDescuento;
	}

	public void setTasaDescuento(double tasaDescuento) {
		this.tasaDescuento = tasaDescuento;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public double getValor_neto() {
		return valor_neto;
	}

	public void setValor_neto(double valor_neto) {
		this.valor_neto = valor_neto;
	}

	public double getValor_recibido() {
		return valor_recibido;
	}

	public void setValor_recibido(double valor_recibido) {
		this.valor_recibido = valor_recibido;
	}

	public double getValor_entregado() {
		return valor_entregado;
	}

	public void setValor_entregado(double valor_entregado) {
		this.valor_entregado = valor_entregado;
	}

	public int getPlazo() {
		return plazo;
	}

	public void setPlazo(int plazo) {
		this.plazo = plazo;
	}

	public double getTCEA() {
		return TCEA;
	}

	public void setTCEA(double tCEA) {
		TCEA = tCEA;
	}

}
