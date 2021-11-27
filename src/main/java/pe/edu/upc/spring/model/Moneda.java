package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Moneda")
public class Moneda implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMoneda;
	
	@Column(name="nombreMoneda", length=40, nullable=false)
	private String nombreMoneda;

	public Moneda() {
		super();
	}

	public Moneda(int idMoneda, String nombreMoneda) {
		super();
		this.idMoneda = idMoneda;
		this.nombreMoneda = nombreMoneda;
	}

	public int getIdMoneda() {
		return idMoneda;
	}

	public void setIdMoneda(int idMoneda) {
		this.idMoneda = idMoneda;
	}

	public String getNombreMoneda() {
		return nombreMoneda;
	}

	public void setNombreMoneda(String nombreMoneda) {
		this.nombreMoneda = nombreMoneda;
	}

}
