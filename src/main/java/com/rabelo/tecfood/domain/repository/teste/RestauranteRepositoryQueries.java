package com.rabelo.tecfood.domain.repository.teste;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rabelo.tecfood.domain.model.Restaurante;

@Repository
public interface RestauranteRepositoryQueries {
	
	List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal);
	List<Restaurante> findComFreteGratis(String nome);

}
