package com.rabelo.tecfood.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabelo.tecfood.domain.model.Restaurante;
import com.rabelo.tecfood.domain.repository.teste.CustomJpaRepository;
import com.rabelo.tecfood.domain.repository.teste.RestauranteRepositoryQueries;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>
            , RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante>{
	
	@Query("from Restaurante r join fetch r.cozinha join fetch r.formaPagamento join fetch r.endereco")
	List<Restaurante> findAll();
		
	@Query("from Restaurante where nome like %:nome% and cozinha.id= :id")
	List<Restaurante> consultarPorNome(String nome, Long id);

	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);

	Optional<Restaurante> findFirstByNomeContaining(String nome);

	List<Restaurante> findTop2ByNomeContaining(String nome);

	int countByCozinhaId(Long cozinha);

		
	@Query(value = "SELECT r FROM Restaurante r where r.nome = :nome and r.cozinha.id = :cozinha")
    Restaurante findByNomeAndCozinha(@Param("nome") String nome, @Param("cozinha") Long cozinha);
	Restaurante findByNome(String nome);
		
	/*List<Restaurante> listar();
	Restaurante salvarRestaurante(Restaurante restaurante);
	Restaurante buscarRestaurante(Long id);
	void remover(Long id);
	*/

}
