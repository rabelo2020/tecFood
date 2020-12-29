package com.rabelo.tecfood.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rabelo.tecfood.domain.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{
	
	Optional<Estado> findByNome(String nome);
	boolean existsByNome(String nome);

}
