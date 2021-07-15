package com.rabelo.tecfood.domain.service;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rabelo.tecfood.domain.model.Cozinha;
import com.rabelo.tecfood.domain.model.Restaurante;
import com.rabelo.tecfood.domain.repository.CozinhaRepository;
import com.rabelo.tecfood.domain.repository.RestauranteRepository;
import com.rabelo.tecfood.domain.service.exception.EntidadeJaCadastradaException;
import com.rabelo.tecfood.domain.service.exception.EntidadeNaoEncontradaException;
import com.rabelo.tecfood.domain.service.exception.RestauranteNaoEncontradaException;

@Service
public class CadastroRestauranteService {
	private static final String MSG_RESTAURANTE_EM_USO = "Restaurante está em uso %d !";

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;

	/*
	 * public Restaurante atualizar(Long id, Restaurante restauranteClient) {
	 * Restaurante restauranteAtual =
	 * restauranteRepository.findById(id).orElse(null);
	 * 
	 * if (restauranteAtual != null) {
	 * 
	 * BeanUtils.copyProperties(restauranteClient, restauranteAtual, "id",
	 * "formaPagamento", "endereco", "dataCadastro", "produtos"); Restaurante
	 * restauranteSalvo = salvar(restauranteAtual); return restauranteSalvo;
	 * 
	 * }
	 * 
	 * return restauranteAtual; }
	 */
	@Transactional
	public Restaurante salvar(Restaurante restaurante) {

		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);

		restaurante.setCozinha(cozinha);
		return restauranteRepository.save(restaurante);

	}

	public Restaurante buscarOuFalhar(Long id) {
		return restauranteRepository.findById(id).orElseThrow(() -> new RestauranteNaoEncontradaException(id));
	}

	@Transactional
	public void excluir(Long id) {
		try {
			restauranteRepository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new RestauranteNaoEncontradaException(id);

		} catch (DataIntegrityViolationException e) {
			throw new RestauranteNaoEncontradaException(String.format(MSG_RESTAURANTE_EM_USO, id));
		}

	}

}
