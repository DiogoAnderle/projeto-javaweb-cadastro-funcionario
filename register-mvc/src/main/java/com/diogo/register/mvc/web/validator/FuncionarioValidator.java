package com.diogo.register.mvc.web.validator;

import java.time.LocalDate;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.diogo.register.mvc.domain.Funcionario;
import com.diogo.register.mvc.service.FuncionarioService;

public class FuncionarioValidator implements Validator{

	private FuncionarioService funcionarioService;
	
	public FuncionarioValidator(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Funcionario.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		
		Funcionario f = (Funcionario) object;
		
		LocalDate entrada = f.getDataEntrada();
		
		
		if (f.getDataSaida() != null) {
			if(f.getDataSaida().isBefore(entrada)) {
				errors.rejectValue("dataSaida", "PosteriorDataEntrada.funcionario.dataSaida");
			}
		}
		
		String nome = f.getNome();
		Long id = f.getId();

		List<Funcionario> funcionarios = funcionarioService.buscarPorNome(nome); 

		if (nome != null) {

		if (funcionarios.size() > 0 && id == null) {

		errors.rejectValue("nome", "Funcionario.ja.existe");

		}

		}
		
	}

}
