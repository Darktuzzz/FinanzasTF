package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Tasa;
import pe.edu.upc.spring.repository.ITasaRepository;
import pe.edu.upc.spring.service.ITasaService;

@Service
public class TasaServiceImpl implements ITasaService {

	@Autowired
	private ITasaRepository dTasa;
	
	@Override
	@Transactional
	public boolean registrar(Tasa tasa) {
		Tasa objTasa = dTasa.save(tasa);
		if (objTasa == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idTasa) {
		dTasa.deleteById(idTasa);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Tasa> listarId(int idTasa) {
		return dTasa.findById(idTasa);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tasa> listar() {
		return dTasa.findAll();
	}

}
