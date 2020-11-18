package com.rabelo.tecfood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rabelo.tecfood.domain.model.Cozinha;

public interface CozinhaRepository extends JpaRepository<Cozinha, Long>{
		

		//List<Cozinha> listar();
		//Cozinha salvarCozinha(Cozinha cozinha);
		//Cozinha buscarCozinha(Long id);
		//void remover(Long id);
		Cozinha findByNome(String nome);

}
