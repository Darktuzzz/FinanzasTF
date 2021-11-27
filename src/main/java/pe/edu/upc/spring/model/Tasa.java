package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Tasa")
public class Tasa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTasa;
	
	@Column(name="nombreTasa", length=30, nullable=false)
	private String nombreTasa;
	
	public Tasa() {
		super();
	}

	public Tasa(int idTasa, String nombreTasa) {
		super();
		this.idTasa = idTasa;
		this.nombreTasa = nombreTasa;
	}

	public int getIdTasa() {
		return idTasa;
	}

	public void setIdTasa(int idTasa) {
		this.idTasa = idTasa;
	}

	public String getNombreTasa() {
		return nombreTasa;
	}

	public void setNombreTasa(String nombreTasa) {
		this.nombreTasa = nombreTasa;
	}

}
