package com.rabelo.tecfood.domain.infrastructure.repository;

import static com.rabelo.tecfood.domain.infrastructure.repository.spec.RestauranteSpecs.comFreteGratis;
import static com.rabelo.tecfood.domain.infrastructure.repository.spec.RestauranteSpecs.comNomeSemelhante;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.rabelo.tecfood.domain.model.Restaurante;
import com.rabelo.tecfood.domain.repository.RestauranteRepository;
import com.rabelo.tecfood.domain.repository.teste.RestauranteRepositoryQueries;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	@Lazy
	private RestauranteRepository restauranteRepository; 

	@Override
	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Restaurante> criteriaQuery = builder.createQuery(Restaurante.class);
		Root<Restaurante> root = criteriaQuery.from(Restaurante.class);

		//List<Predicate> predicates = new ArrayList<>();
		 var predicates = new ArrayList<Predicate>();

		if (StringUtils.hasText(nome)) {
			predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));
		}

		if (taxaFreteInicial != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
		}

		if (taxaFreteFinal != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
		}

		criteriaQuery.where(predicates.toArray(new Predicate[0]));

		TypedQuery<Restaurante> query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();

		/*
		 * var jpql
		 * ="from Restaurante where nome like :nome and taxaFrete between :taxaInicial and :taxaFinal"
		 * ; return entityManager.createQuery(jpql, Restaurante.class)
		 * .setParameter("nome", "%" + nome + "%") .setParameter("taxaInicial",
		 * taxaInicial) .setParameter("taxaFinal", taxaFinal) .getResultList();
		 */
	}

	@Override
	public List<Restaurante> findComFreteGratis(String nome) {
		
		return restauranteRepository
				.findAll(comFreteGratis().and(comNomeSemelhante(nome)));
	}
}
