package com.rabelo.tecfood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rabelo.tecfood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository {
	
	List<Restaurante> listar();
	void salvarRestaurante(Restaurante restaurante);
	Restaurante buscarRestaurante(Long id);
	void remover(Long id);
	

}
