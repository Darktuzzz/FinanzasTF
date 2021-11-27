package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.TipoTasa;

public interface ITipoTasaService {
	public boolean registrar(TipoTasa tipoTasa);
	public void eliminar(int idTipoTasa);
	public Optional<TipoTasa> listarId(int idTipoTasa);
	List<TipoTasa> listar();	
}
