package com.rabelo.tecfood.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabelo.tecfood.domain.model.Cidade;
import com.rabelo.tecfood.domain.model.Estado;
import com.rabelo.tecfood.domain.repository.CidadeRepository;
import com.rabelo.tecfood.domain.repository.EstadoRepository;
import com.rabelo.tecfood.domain.service.exception.EntidadeNaoEncontradaException;

@Service
public class CadastroCidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	public Cidade salvar(Cidade cidade) {

		Estado estado = estadoRepository.findById(cidade.getEstado().getId()).orElse(null);

		if (estado == null) {
			throw new EntidadeNaoEncontradaException("Código do estado não está cadastrada!");
		}

		return cidadeRepository.save(cidade);

	}

	public Cidade atualizar(Long id, Cidade cidade) {
		Cidade cidadeAtual = buscarCidadeId(id);

		if (cidadeAtual != null) {

			BeanUtils.copyProperties(cidade, cidadeAtual, "id");
			return salvar(cidadeAtual);
		}
		throw new EntidadeNaoEncontradaException("Código da Cidade não encotrada!");

	}


	private Cidade buscarCidadeId(Long id) {
		Cidade cidadeAtual = cidadeRepository.findById(id).orElse(null);
		return cidadeAtual;
	}

}
