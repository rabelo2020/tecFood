package com.rabelo.tecfood.domain.repository.teste;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rabelo.tecfood.domain.model.Cozinha;

@Repository
public interface CozinhasRepository extends CustomJpaRepository<Cozinha, Long>{
	
	//List<Cozinha> buscarPorNome(String nome);
	List<Cozinha> findByNomeContaining(String nome);
	//Optional<Cozinha> findByNomeContaining(String nome);
	boolean existsByNome(String nome);
	

}
