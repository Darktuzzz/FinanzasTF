package pe.edu.upc.spring.serviceimpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Cartera;
import pe.edu.upc.spring.model.Letra;
import pe.edu.upc.spring.repository.ILetraRepository;
import pe.edu.upc.spring.service.ILetraService;
import pe.edu.upc.spring.repository.ICarteraRepository;

@Service
public class LetraServiceImpl implements ILetraService {

	@Autowired
	private ILetraRepository dLetra;

	@Autowired
	private ICarteraRepository dCartera;

	@Override
	@Transactional
	public boolean registrar(Letra letra) {
		Letra objLetra = dLetra.save(letra);
		Cartera objCartera = new Cartera();
		// Plazo
		long dif = objLetra.getFecha_vencimiento().getTime() - objLetra.getFecha_emision().getTime();
		TimeUnit time = TimeUnit.DAYS;
		long diffrence = time.convert(dif, TimeUnit.MILLISECONDS);

		double ta = 0;
		if (objLetra.getTasa().getIdTasa() == 2) {
			if (objLetra.getTipoTasa().getIdTipoTasa() == 1) {
				double te1 = ((Math.pow(1 + (objLetra.getValorTasa() / 100), (double)diffrence / 30))-1) *100;
				ta = te1;
			}
			else if (objLetra.getTipoTasa().getIdTipoTasa() == 2) {
				double te2 = ((Math.pow(1 + (objLetra.getValorTasa() / 100), (double)diffrence / 180))-1) *100;
				ta = te2;
			}
			else {
				double te3 = ((Math.pow(1 + (objLetra.getValorTasa() / 100), (double)diffrence / 360))-1) *100;
				ta = te3;
			}
			
		} else if (objLetra.getTasa().getIdTasa() == 1) {
			if (objLetra.getTipoTasa().getIdTipoTasa() == 1) {
				double tn1 = ((Math.pow(1 + ((objLetra.getValorTasa()/100)/30), (double)diffrence))-1)*100;
				ta = tn1;
			}
			else if (objLetra.getTipoTasa().getIdTipoTasa() == 2) {
				double tn2 = ((Math.pow(1 + ((objLetra.getValorTasa()/100)/180), (double)diffrence))-1)*100;
				ta = tn2;
			}
			else {
				double tn3 = ((Math.pow(1 + ((objLetra.getValorTasa()/100)/360), (double)diffrence))-1)*100;
				ta = tn3;
			}
		}
		
		double tasaDescuento = ((ta/100)/(1+(ta/100)))*100;
		double valorNeto = Integer.parseInt(objLetra.getValor_nominal()) * (1 - (tasaDescuento / 100));
		double descuento = Integer.parseInt(objLetra.getValor_nominal()) - valorNeto;

		double valorEntregado = Double.valueOf(objLetra.getValor_nominal()) + objLetra.getCostes_gastos();
		double valorRecibido = valorNeto - objLetra.getCostes_gastos();
		double num = valorEntregado / valorRecibido;
		double d = 360 / (double) diffrence;
		double tcea = (Math.pow(num, d) - 1) * 100;
		/*
		 * BigDecimal bd = new BigDecimal(tcea); bd = bd.setScale(2,
		 * RoundingMode.HALF_UP); double t = (double)bd;
		 */
		// double a = (double)Math.round(tcea * 100d)/100;

		String tasaDescuento_ = String.format("%.8f", tasaDescuento);
		String descuento_ = String.format("%.2f", descuento);
		String valorNeto_ = String.format("%.2f", valorNeto);
		String valorEntregado_ = String.format("%.2f", valorEntregado);
		String valorRecibido_ = String.format("%.2f", valorRecibido);
		String tcea_ = String.format("%.8f", tcea);

		if (objLetra == null)
			return false;
		else {
			objCartera.setLetra(objLetra);
			objCartera.setPlazo((int) diffrence);
			objCartera.setTasaConvertida(ta);
			objCartera.setTasaDescuento(Double.valueOf(tasaDescuento_));
			objCartera.setDescuento(Double.valueOf(descuento_));
			objCartera.setValor_neto(Double.valueOf(valorNeto_));
			objCartera.setValor_entregado(Double.valueOf(valorEntregado_));
			objCartera.setValor_recibido(Double.valueOf(valorRecibido_));
			objCartera.setTCEA(Double.valueOf(tcea_));
			dCartera.save(objCartera);
			return true;
		}
	}

	@Override
	@Transactional
	public void eliminar(int idLetra) {
		dLetra.deleteById(idLetra);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Letra> listarId(int idLetra) {
		return dLetra.findById(idLetra);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Letra> listar() {
		return dLetra.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Letra> buscarValor(String valor_nominal) {
		return dLetra.buscarValor(valor_nominal);
	}

}
