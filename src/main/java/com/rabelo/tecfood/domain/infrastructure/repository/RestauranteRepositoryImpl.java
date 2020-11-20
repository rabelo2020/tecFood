package com.rabelo.tecfood.domain.infrastructure.repository;

import org.springframework.stereotype.Component;

@Component
public class RestauranteRepositoryImpl{// implements RestauranteRepository{
/*	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Restaurante> listar() {
		
		return entityManager.createQuery("from Restaurante", Restaurante.class)
				.getResultList();
	}

	@Transactional
	@Override
	public Restaurante salvarRestaurante(Restaurante restaurante) {
		return entityManager.merge(restaurante);
		
	}

	@Override
	public Restaurante buscarRestaurante(Long id) {

		return entityManager.find(Restaurante.class, id);
	}

	
	@Transactional
	@Override
	public void remover(Long id) {
		
		entityManager.remove(buscarRestaurante(id));
		
	}
  */
}
