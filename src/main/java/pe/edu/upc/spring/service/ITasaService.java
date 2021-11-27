package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Tasa;

public interface ITasaService {
	public boolean registrar(Tasa tasa);
	public void eliminar(int idTasa);
	public Optional<Tasa> listarId(int idTasa);
	List<Tasa> listar();	
}
