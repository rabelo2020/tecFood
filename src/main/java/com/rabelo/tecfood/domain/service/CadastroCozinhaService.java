package com.rabelo.tecfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.rabelo.tecfood.domain.model.Cozinha;
import com.rabelo.tecfood.domain.repository.CozinhaRepository;
import com.rabelo.tecfood.domain.service.exception.CozinhaNaoEncontradaException;
import com.rabelo.tecfood.domain.service.exception.EntidadeEmUsoException;
import com.rabelo.tecfood.domain.service.exception.EntidadeJaCadastradaException;
import com.rabelo.tecfood.domain.service.exception.EntidadeNaoEncontradaException;

@Service
public class CadastroCozinhaService {

	private static final String MSG_COZINHA_ESTA_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso";
	private static final String MSG_COZINHA_JA_CADASTRADA = "COZINHA DE NOME %s JÁ CADASTRADA !";

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Transactional
	public Cozinha salva(Cozinha cozinha) {
		existeCozinha(cozinha.getNome());
		return cozinhaRepository.save(cozinha);
		/*
		 * Cozinha cozinhaAtual = cozinhaRepository.findByNome(cozinha.getNome());
		 * 
		 * if (cozinhaAtual != null) {
		 * 
		 * throw new EntidadeJaCadastradaException("Cozinha já existente no BD!"); }
		 * return cozinhaRepository.save(cozinha);
		 */
	}

	@Transactional
	public void excluir(Long id) {

		try {

			cozinhaRepository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {

			throw new CozinhaNaoEncontradaException(id);

		} catch (DataIntegrityViolationException e) {

			throw new EntidadeEmUsoException(String.format(MSG_COZINHA_ESTA_EM_USO, id));

		}
	}

	public Cozinha buscarOuFalhar(Long cozinhaId) {
		return cozinhaRepository.findById(cozinhaId).orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));
	}

	public void existeCozinha(String nome) {
		boolean cozinha = cozinhaRepository.existsByNome(nome.trim());

		if (cozinha) {

			throw new CozinhaNaoEncontradaException(String.format(MSG_COZINHA_JA_CADASTRADA, nome.toUpperCase()));
		}

	}

}
