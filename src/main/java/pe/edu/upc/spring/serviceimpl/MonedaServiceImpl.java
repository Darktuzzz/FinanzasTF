package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Moneda;
import pe.edu.upc.spring.repository.IMonedaRepository;
import pe.edu.upc.spring.service.IMonedaService;

@Service
public class MonedaServiceImpl implements IMonedaService {

	@Autowired
	private IMonedaRepository dMoneda;
	
	@Override
	@Transactional
	public boolean registrar(Moneda moneda) {
		Moneda objMoneda = dMoneda.save(moneda);
		if (objMoneda == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idMoneda) {
		dMoneda.deleteById(idMoneda);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Moneda> listarId(int idMoneda) {
		return dMoneda.findById(idMoneda);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Moneda> listar() {
		return dMoneda.findAll();
	}

}
