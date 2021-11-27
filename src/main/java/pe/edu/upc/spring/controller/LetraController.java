package pe.edu.upc.spring.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Letra;
import pe.edu.upc.spring.model.Moneda;
import pe.edu.upc.spring.model.Tasa;
import pe.edu.upc.spring.model.TipoTasa;
import pe.edu.upc.spring.service.ILetraService;
import pe.edu.upc.spring.service.IMonedaService;
import pe.edu.upc.spring.service.ITasaService;
import pe.edu.upc.spring.service.ITipoTasaService;

@Controller
@RequestMapping("/letra")
public class LetraController {
	@Autowired
	private ILetraService rService;
	
	@Autowired
	private IMonedaService mService;
	
	@Autowired
	private ITasaService tService;
	
	@Autowired
	private ITipoTasaService zService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
		
	@RequestMapping("/")
	public String irPaginaListadoRazas(Map<String, Object> model) {
		model.put("listaLetras", rService.listar());
		return "listLetra";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("letra", new Letra());
		model.addAttribute("moneda", new Moneda());
		model.addAttribute("tasa", new Tasa());
		model.addAttribute("tipoTasa", new TipoTasa());
		
		model.addAttribute("listaMonedas", mService.listar());
		model.addAttribute("listaTasas", tService.listar());
		model.addAttribute("listaTiposTasas", zService.listar());
		return "letra";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@Valid Letra objRace, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaMonedas", mService.listar());
			model.addAttribute("listaTasas", tService.listar());
			model.addAttribute("listaTiposTasas", zService.listar());
			return "letra";
		}
		else {
			boolean flag = rService.registrar(objRace);
			if (flag)
				return "redirect:/letra/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/letra/irRegistrar";
			}
		}
	}
	
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<Letra> objLetra = rService.listarId(id);
		if (objLetra == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/letra/listar";
		}
		else {
			model.addAttribute("listaMonedas", mService.listar());
			model.addAttribute("listaTasas", tService.listar());
			model.addAttribute("listaTiposTasas", zService.listar());
			if (objLetra.isPresent()){
				objLetra.ifPresent(o -> model.addAttribute("letra", objLetra));
			}
			return "letra";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				rService.eliminar(id);
				model.put("listaLetras", rService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Error, esta letra pertenece a la cartera");
			model.put("listaLetras", rService.listar());
			
		}
		return "listLetra";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaLetras", rService.listar());
		return "listLetra";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) 
	{
		model.addAttribute("letra", new Letra());
		return "buscar";
	}	
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Letra letra)
			throws ParseException
	{
		List<Letra> listaLetras;
		letra.setValor_nominal(letra.getValor_nominal());
		listaLetras = rService.buscarValor(letra.getValor_nominal());
		if (listaLetras.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		model.put("listaLetras", listaLetras);		
		return "buscar";
	}
}
