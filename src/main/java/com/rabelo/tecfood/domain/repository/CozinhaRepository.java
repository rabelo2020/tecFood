package com.rabelo.tecfood.domain.repository;

import java.util.List;

import com.rabelo.tecfood.domain.model.Cozinha;
import com.rabelo.tecfood.domain.model.Restaurante;

public interface CozinhaRepository {
		

		List<Cozinha> listar();
		void salvarCozinha(Cozinha cozinha);
		Cozinha buscarCozinha(Long id);
		void remover(Long id);

}
