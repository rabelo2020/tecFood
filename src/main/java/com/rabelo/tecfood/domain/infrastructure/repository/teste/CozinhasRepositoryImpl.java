package com.rabelo.tecfood.domain.infrastructure.repository.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.rabelo.tecfood.domain.model.Cozinha;
import com.rabelo.tecfood.domain.repository.teste.CozinhasRepository;


public class CozinhasRepositoryImpl  {
	
	@PersistenceContext
	private EntityManager entityManager;

	
	public List<Cozinha> buscarPorNome(String nome) {

		return entityManager.createQuery("from Cozinha c where c.nome like :nome", Cozinha.class)
				            .setParameter("nome", "%" + nome + "%")
				            .getResultList();
	}

	
	public List<Cozinha> findByNome(String nome) {
		// TODO Auto-generated method stub
		return entityManager.createQuery("from Cozinha", Cozinha.class)
				            .getResultList();
	}

}
