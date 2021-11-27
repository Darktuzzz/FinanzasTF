package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.TipoTasa;
import pe.edu.upc.spring.repository.ITipoTasaRepository;
import pe.edu.upc.spring.service.ITipoTasaService;

@Service
public class TipoTasaServiceImpl implements ITipoTasaService {

	@Autowired
	private ITipoTasaRepository dTipoTasa;
	
	@Override
	@Transactional
	public boolean registrar(TipoTasa tasa) {
		TipoTasa objTipoTasa = dTipoTasa.save(tasa);
		if (objTipoTasa == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idTipoTasa) {
		dTipoTasa.deleteById(idTipoTasa);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<TipoTasa> listarId(int idTipoTasa) {
		return dTipoTasa.findById(idTipoTasa);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TipoTasa> listar() {
		return dTipoTasa.findAll();
	}

}
