package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Cartera;
import pe.edu.upc.spring.repository.ICarteraRepository;
import pe.edu.upc.spring.service.ICarteraService;

@Service
public class CarteraServiceImpl implements ICarteraService {

	@Autowired
	private ICarteraRepository dCartera;
	
	@Override
	@Transactional
	public boolean registrar(Cartera cartera) {
		Cartera objCartera = dCartera.save(cartera);
		if (objCartera == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idCartera) {
		dCartera.deleteById(idCartera);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Cartera> listarId(int idCartera) {
		return dCartera.findById(idCartera);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cartera> listar() {
		return dCartera.findAll();
	}

}
