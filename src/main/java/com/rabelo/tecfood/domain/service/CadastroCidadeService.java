package com.rabelo.tecfood.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rabelo.tecfood.domain.model.Cidade;
import com.rabelo.tecfood.domain.model.Estado;
import com.rabelo.tecfood.domain.repository.CidadeRepository;
import com.rabelo.tecfood.domain.repository.EstadoRepository;
import com.rabelo.tecfood.domain.service.exception.CidadeNaoEncontradaException;
import com.rabelo.tecfood.domain.service.exception.EntidadeEmUsoException;
import com.rabelo.tecfood.domain.service.exception.NegocioException;

@Service
public class CadastroCidadeService {

	private static final String MSG_CIDADE_OU_ESTADO_JA_EXISTENTE = "Nome da Cidade %s já cadastrada, no Estado de código %d !";
	private static final String MSG_CIDADE_EM_USO = "Código %d da Cidade está em uso!";

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CadastroEstadoService cadastroEstadoService;

	@Transactional
	public Cidade salvar(Cidade cidade) {
		
		Long estadoId = cidade.getEstado().getId();
		Estado estado = cadastroEstadoService.buscarOuFalhar(estadoId);
		existsCidadeAndEstado(estadoId, cidade.getNome().trim());

		cidade.setEstado(estado);

		return cidadeRepository.save(cidade);

		/*
		 * Estado estado =
		 * estadoRepository.findById(cidade.getEstado().getId()).orElse(null);
		 * 
		 * if (estado == null) { throw new
		 * EntidadeNaoEncontradaException(String.format(ESTADO_NAO__CADASTRADA,
		 * estado.getId())); }
		 * 
		 * return cidadeRepository.save(cidade);
		 */

	}
    @Transactional
	public Cidade atualizar(Long id, Cidade cidade) {
		Cidade cidadeAtual = buscarCidadeId(id);

		if (cidadeAtual != null) {

			BeanUtils.copyProperties(cidade, cidadeAtual, "id");
			return salvar(cidadeAtual);
		}
		throw new CidadeNaoEncontradaException(id);

	}

	private Cidade buscarCidadeId(Long id) {
		Cidade cidadeAtual = cidadeRepository.findById(id).orElse(null);
		return cidadeAtual;
	}

	@Transactional
	public void excluir(Long cidadeId) {
		try {
			cidadeRepository.deleteById(cidadeId);

		} catch (EmptyResultDataAccessException e) {
			throw new CidadeNaoEncontradaException(cidadeId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_CIDADE_EM_USO, cidadeId));
		}
	}

	public Cidade buscarOuFalhar(Long id) {

		return cidadeRepository.findById(id).orElseThrow(() -> new CidadeNaoEncontradaException(id));
	}

	public void existsCidadeAndEstado(Long estadoId, String nome) {
		boolean cidade = cidadeRepository.existsByNomeAndEstadoId(nome, estadoId);
		if (cidade) {
			throw new NegocioException(String.format(MSG_CIDADE_OU_ESTADO_JA_EXISTENTE, nome.toUpperCase(), estadoId));
		}

	}

}
