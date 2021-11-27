package pe.edu.upc.spring.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Cartera;
import pe.edu.upc.spring.model.Letra;
import pe.edu.upc.spring.service.ICarteraService;
import pe.edu.upc.spring.service.ILetraService;

@Controller
@RequestMapping("/cartera")
public class CarteraController {
	@Autowired
	private ICarteraService rService;
	
	@Autowired
	private ILetraService raService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
		
	@RequestMapping("/")
	public String irPaginaListadoRazas(Map<String, Object> model) {
		model.put("listaCarteras", rService.listar());
		return "listCartera";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("cartera", new Cartera());
		model.addAttribute("race", new Letra());
		
		model.addAttribute("listaLetras", raService.listar());
		
		return "cartera";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@Valid Cartera objDueno, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors())
		{
			model.addAttribute("listaLetras", raService.listar());
			return "cartera";
		}
		else {
			boolean flag = rService.registrar(objDueno);
			if (flag)
				return "redirect:/cartera/listar";
			else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/cartera/irRegistrar";
			}
		}
	}
	
	/*
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<Cartera> objDueno = rService.listarId(id);
		if (objDueno == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/dueno/listar";
		}
		else {
			model.addAttribute("dueno", objDueno);
			return "dueno";
		}
	}
	*/
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				rService.eliminar(id);
				model.put("listaCarteras", rService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un problema");
			model.put("listaCarteras", rService.listar());
			
		}
		return "listCartera";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaCarteras", rService.listar());
		return "listCartera";
	}		
}
