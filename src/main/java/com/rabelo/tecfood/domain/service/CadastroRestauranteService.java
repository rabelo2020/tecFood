package com.rabelo.tecfood.domain.service;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabelo.tecfood.domain.model.Cozinha;
import com.rabelo.tecfood.domain.model.Restaurante;
import com.rabelo.tecfood.domain.repository.CozinhaRepository;
import com.rabelo.tecfood.domain.repository.RestauranteRepository;
import com.rabelo.tecfood.domain.service.exception.EntidadeJaCadastradaException;
import com.rabelo.tecfood.domain.service.exception.EntidadeNaoEncontradaException;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Restaurante atualizar(Long id, Restaurante restauranteClient) {
		Restaurante restauranteAtual = restauranteRepository.findById(id).orElse(null);

		if (restauranteAtual != null) {

			BeanUtils.copyProperties(restauranteClient, restauranteAtual, "id");
			Restaurante restauranteSalvo = salvar(restauranteAtual);
			return restauranteSalvo;

		}

		return restauranteAtual;
	}

	public Restaurante salvar(Restaurante restaurante) {
		/*
		 * Cozinha cozinhaAtual =
		 * cozinhaRepository.findById(restaurante.getCozinha().getId()).orElse(null); if
		 * (cozinhaAtual == null) { throw new
		 * EntidadeNaoEncontradaException("Código de Cozinha não existente no Cadastro!"
		 * ); }
		 */
		Cozinha cozinha = cozinhaRepository.findById(restaurante.getCozinha().getId())
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Código de Cozinha não existente no Cadastro!"));

		restaurante.setCozinha(cozinha);
		return restauranteRepository.save(restaurante);

	}

}
