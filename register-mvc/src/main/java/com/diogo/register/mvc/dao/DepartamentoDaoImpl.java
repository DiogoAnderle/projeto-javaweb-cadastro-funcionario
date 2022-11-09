package com.diogo.register.mvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.diogo.register.mvc.domain.Departamento;

@Repository
public class DepartamentoDaoImpl extends AbstractDao<Departamento, Long> implements DepartamentoDao {

	@Override
	public List<Departamento> findByName(String nome) {

		return createQuery("select d from Departamento d where d.nome like concat('%',?1,'%') ", nome);
	}

}
