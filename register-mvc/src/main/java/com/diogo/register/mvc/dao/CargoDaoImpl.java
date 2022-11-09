package com.diogo.register.mvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.diogo.register.mvc.domain.Cargo;
import com.diogo.register.mvc.util.PaginationUtil;

@Repository
public class CargoDaoImpl extends AbstractDao<Cargo, Long> implements CargoDao {
		
	public List<Cargo> findByNome(String nome) {
		
		return createQuery("select c from Cargo c where c.nome like concat('%',?1,'%') ", nome);
	}


	public PaginationUtil<Cargo> buscaPaginada(int pagina){
		
		int tamanho = 5;
		int inicio = (pagina - 1) * tamanho; //0*5=0, 1*5=5, 2*5=10
		List<Cargo> cargos = getEntityManager()
				.createQuery("select c from Cargo c order by c.nome asc", Cargo.class)
				.setFirstResult(inicio)
				.setMaxResults(tamanho)
				.getResultList();
		
		long totalRegistros = count();
		long totalDePaginas = (totalRegistros + (tamanho-1)) / tamanho;
		
		return new PaginationUtil<>(tamanho, pagina, totalDePaginas, cargos);
	}

	public long count() {
			return getEntityManager()
					.createQuery("select count(*) from Cargo", Long.class)
					.getSingleResult();
	}

}
