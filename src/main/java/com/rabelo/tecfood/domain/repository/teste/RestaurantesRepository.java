package com.rabelo.tecfood.domain.repository.teste;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rabelo.tecfood.domain.model.Restaurante;

@Repository
public interface RestaurantesRepository extends JpaRepository<Restaurante, Long>{
	
	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);	
	Optional<Restaurante> findFirstByNomeContaining(String nome);
	List<Restaurante> findTop2ByNomeContaining(String nome);
	int countByCozinhaId(Long cozinha);

}