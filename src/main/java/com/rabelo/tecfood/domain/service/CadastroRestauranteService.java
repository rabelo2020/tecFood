package com.rabelo.tecfood.domain.service;

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

	public Restaurante salvar(Restaurante restaurante) {
		// Long cozinhaId = restaurante.getCozinha().getId();
		Restaurante restauranteAtual = restauranteRepository.findByNome(restaurante.getNome().trim());
		Cozinha cozinhaAtual = cozinhaRepository.findById(restaurante.getCozinha().getId()).orElse(null);

		if (restauranteAtual != null) {

			throw new EntidadeJaCadastradaException("Nome do Restaurante já existente no Cadastro!");

		}
		if (cozinhaAtual == null) {
			throw new EntidadeNaoEncontradaException("Código de Cozinha não existente no Cadastro!");
		}
		return restauranteRepository.save(restaurante);

	}

	public Restaurante atualizar(Long id, Restaurante restauranteClient) {
		Restaurante restauranteAtual = restauranteRepository.findById(id).orElse(null);

		if (restauranteAtual != null) {

			BeanUtils.copyProperties(restauranteClient, restauranteAtual, "id");
			return salvar(restauranteAtual);

		}

		return restauranteAtual;
	}

}
