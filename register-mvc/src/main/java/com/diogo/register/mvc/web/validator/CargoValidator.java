package com.diogo.register.mvc.web.validator;
 
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.diogo.register.mvc.domain.Cargo;
import com.diogo.register.mvc.service.CargoService;
 
public class CargoValidator implements Validator{
 
	private CargoService cargoService;

	public CargoValidator(CargoService cargoService) {
		this.cargoService = cargoService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Cargo.class.equals(clazz);
	}
 
	@Override
	public void validate(Object object, Errors errors) {
 
		Cargo c = (Cargo) object;
		
		String nome = c.getNome();
		Long id = c.getId();
		List<Cargo> cargos = cargoService.buscarPorNome(nome);
		
		if (cargos != null && id == null) {
			if (cargos.size() > 0) {
				errors.rejectValue("nome", "Cargo.ja.existe");
			}
		}
	}
} 