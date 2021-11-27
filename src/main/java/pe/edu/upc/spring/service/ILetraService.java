package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Letra;

public interface ILetraService {
	public boolean registrar(Letra letra);
	public void eliminar(int idLetra);
	public Optional<Letra> listarId(int idLetra);
	List<Letra> listar();
	List<Letra> buscarValor(String valor_nominal);
	
}
