package com.diogo.register.mvc.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.diogo.register.mvc.domain.Departamento;
import com.diogo.register.mvc.service.DepartamentoService;
import com.diogo.register.mvc.web.validator.DepartamentoValidator;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoService departamentoService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Departamento departamento) {
		return "/departamento/cadastro";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new DepartamentoValidator(departamentoService));
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("departamentos", departamentoService.buscarTodos());
		return "/departamento/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Departamento departamento,BindingResult result,  RedirectAttributes attr) {
		
		if(result.hasErrors()) {
			return "/departamento/cadastro";
		}
		
		departamentoService.salvar(departamento);
		attr.addFlashAttribute("success", "Departamento inserido com sucesso.");
		return "redirect:/departamentos/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("departamento", departamentoService.buscarPorId(id));
		return "/departamento/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Departamento departamento,BindingResult result, RedirectAttributes attr) {
		
		if(result.hasErrors()) {
			return "/departamento/cadastro";
		}
		
		departamentoService.editar(departamento);
		attr.addFlashAttribute("success", "Departamento editado com sucesso.");
		return "redirect:/departamentos/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		
		if (departamentoService.departamentoTemCargo(id)) {
			model.addAttribute("fail", "Departamento n??o pode ser removido, possui cargo(s) vinculado(s).");
		} else {
			departamentoService.excluir(id);
			model.addAttribute("success", "Departamento exclu??do com sucesso.");
			
		};
		return listar(model);
		
	}
}
