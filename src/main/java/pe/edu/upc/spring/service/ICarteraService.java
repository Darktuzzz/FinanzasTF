package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Cartera;

public interface ICarteraService {
	public boolean registrar(Cartera cartera);
	public void eliminar(int idCartera);
	public Optional<Cartera> listarId(int idCartera);
	List<Cartera> listar();	
}
