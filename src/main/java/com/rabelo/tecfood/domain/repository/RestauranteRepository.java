package com.rabelo.tecfood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabelo.tecfood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>{
	
	/*List<Restaurante> listar();
	Restaurante salvarRestaurante(Restaurante restaurante);
	Restaurante buscarRestaurante(Long id);
	void remover(Long id);
	*/
	@Query(value = "SELECT r FROM Restaurante r where r.nome = :nome and r.cozinha.id = :cozinha")
    Restaurante findByNomeAndCozinha(@Param("nome") String nome, @Param("cozinha") Long cozinha);
	Restaurante findByNome(String nome);
	
	

}
