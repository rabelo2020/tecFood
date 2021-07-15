package com.rabelo.tecfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rabelo.tecfood.domain.model.Estado;
import com.rabelo.tecfood.domain.repository.EstadoRepository;
import com.rabelo.tecfood.domain.service.exception.EntidadeEmUsoException;
import com.rabelo.tecfood.domain.service.exception.EntidadeJaCadastradaException;
import com.rabelo.tecfood.domain.service.exception.EntidadeNaoEncontradaException;
import com.rabelo.tecfood.domain.service.exception.EstadoNaoEncontradoException;

@Service
public class CadastroEstadoService {

	private static final String MSG_ESTADO_ESTA_USO = "ESTADO COM CÓDIGO %d, ESTÁ EM USO!";
	private static final String MSG_ESTADO_ESTA_CADASTRADA = "Estado com nome %s, já está Cadastrado!";
	

	@Autowired
	private EstadoRepository estadoRepository;

	@Transactional
	public Estado salvar(Estado estado) {
		existeEstado(estado.getNome());

		return estadoRepository.save(estado);
		/*
		 * Estado estadoId = estadoRepository.findByNome(estado.getNome());
		 * 
		 * if (estadoId != null) { throw new
		 * EntidadeJaCadastradaException("Nome do Estado já existe no BD !"); } return
		 * estadoRepository.save(estado);
		 */
	}

	public void existeEstado(String nome) {

		boolean estado = estadoRepository.existsByNome(nome.trim());
		if (estado) {
			throw new EntidadeJaCadastradaException(String.format(MSG_ESTADO_ESTA_CADASTRADA, nome.toUpperCase()));
		}

	}

	public Estado buscarOuFalhar(Long estadoId) {

		return estadoRepository.findById(estadoId)
				.orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));

	}

	@Transactional
	public void excluir(Long id) {
		try {
			Estado estado = buscarOuFalhar(id);
			estadoRepository.delete(estado);

		} catch (DataIntegrityViolationException e) {

			throw new EstadoNaoEncontradoException(String.format(MSG_ESTADO_ESTA_USO, id));
			
		}catch (EmptyResultDataAccessException e) {
			
			throw new EstadoNaoEncontradoException(id);
		}

	}

}
