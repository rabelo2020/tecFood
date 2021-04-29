package com.rabelo.tecfood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rabelo.tecfood.domain.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
//extends PagingAndSortingRepository<Cidade, Long>{ 

	
	Cidade findByNome(String nome);
	boolean existsByNomeAndEstadoId(String nome, Long estadoId);

}
