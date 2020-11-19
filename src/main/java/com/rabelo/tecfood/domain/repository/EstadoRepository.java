package com.rabelo.tecfood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rabelo.tecfood.domain.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{
	
	Estado findByNome(String nome);

}
