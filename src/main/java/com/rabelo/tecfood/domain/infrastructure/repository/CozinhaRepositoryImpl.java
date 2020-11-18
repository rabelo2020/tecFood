package com.rabelo.tecfood.domain.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.rabelo.tecfood.domain.model.Cozinha;
import com.rabelo.tecfood.domain.repository.CozinhaRepository;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Cozinha> listar() {
		
		return entityManager.createQuery("from Cozinha", Cozinha.class).getResultList();
	}

	@Transactional
	@Override
	public Cozinha salvarCozinha(Cozinha cozinha) {
		return entityManager.merge(cozinha);
		
	}

	@Override
	public Cozinha buscarCozinha(Long id) {

		return entityManager.find(Cozinha.class, id);
	}

	@Transactional
	@Override
	public void remover(Long id) {
		entityManager.remove(buscarCozinha(id));
		
	}

}
