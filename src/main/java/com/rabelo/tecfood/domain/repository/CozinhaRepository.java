package com.rabelo.tecfood.domain.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.rabelo.tecfood.domain.model.Cozinha;
import com.rabelo.tecfood.domain.repository.teste.CustomJpaRepository;

public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long>, JpaSpecificationExecutor<Cozinha>{
		

		//List<Cozinha> listar();
		//Cozinha salvarCozinha(Cozinha cozinha);
		//Cozinha buscarCozinha(Long id);
		//void remover(Long id);
		Cozinha findByNome(String nome);
		boolean existsByNome(String nome);

}
