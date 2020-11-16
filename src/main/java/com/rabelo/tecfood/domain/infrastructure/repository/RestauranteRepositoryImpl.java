package com.rabelo.tecfood.domain.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.rabelo.tecfood.domain.model.Restaurante;
import com.rabelo.tecfood.domain.repository.RestauranteRepository;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Restaurante> listar() {
		
		return entityManager.createQuery("from Restaurante", Restaurante.class)
				.getResultList();
	}

	@Override
	public void salvarRestaurante(Restaurante restaurante) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Restaurante buscarRestaurante(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remover(Long id) {
		// TODO Auto-generated method stub
		
	}

}
