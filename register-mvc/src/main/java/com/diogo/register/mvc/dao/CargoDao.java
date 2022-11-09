package com.diogo.register.mvc.dao;

import java.util.List;

import com.diogo.register.mvc.domain.Cargo;
import com.diogo.register.mvc.util.PaginationUtil;

public interface CargoDao {

	void save(Cargo cargo);
	
	void update(Cargo cargo);
	
	void delete(Long id);
	
	Cargo findById(Long id);
	
	List<Cargo> findByNome(String nome);
	
	List<Cargo> findAll();
	
	PaginationUtil<Cargo> buscaPaginada(int pagina);

}
