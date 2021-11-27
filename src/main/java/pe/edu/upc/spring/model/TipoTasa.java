package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TipoTasa")
public class TipoTasa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTipoTasa;
	
	@Column(name="nombreTipoTasa", length=30, nullable=false)
	private String nombreTipoTasa;

	public TipoTasa() {
		super();
	}

	public TipoTasa(int idTipoTasa, String nombreTipoTasa) {
		super();
		this.idTipoTasa = idTipoTasa;
		this.nombreTipoTasa = nombreTipoTasa;
	}

	public int getIdTipoTasa() {
		return idTipoTasa;
	}

	public void setIdTipoTasa(int idTipoTasa) {
		this.idTipoTasa = idTipoTasa;
	}

	public String getNombreTipoTasa() {
		return nombreTipoTasa;
	}

	public void setNombreTipoTasa(String nombreTipoTasa) {
		this.nombreTipoTasa = nombreTipoTasa;
	}

}
