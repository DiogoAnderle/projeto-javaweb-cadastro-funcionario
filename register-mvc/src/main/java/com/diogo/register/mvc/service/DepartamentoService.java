package com.diogo.register.mvc.service;

import java.util.List;

import com.diogo.register.mvc.domain.Departamento;

public interface DepartamentoService {
void salvar(Departamento departamento);
	
	void editar(Departamento departamento);
	
	void excluir(Long id);
	
	Departamento buscarPorId(Long id);
	
	List<Departamento> buscarTodos();
	
	List<Departamento> buscarPorNome(String nome);

	boolean departamentoTemCargo(Long id);
}
