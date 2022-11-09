package com.diogo.register.mvc.dao;

import java.util.List;

import com.diogo.register.mvc.domain.Departamento;

public interface DepartamentoDao {
	
	void save(Departamento departamento);
	
	void update(Departamento departamento);
	
	void delete(Long id);
	
	List<Departamento> findByName(String nome);
	
	Departamento findById(Long id);
	
	List<Departamento> findAll();

}
