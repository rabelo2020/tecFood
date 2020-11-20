package com.rabelo.tecfood.domain.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.rabelo.tecfood.domain.model.FormaPagamento;
import com.rabelo.tecfood.domain.repository.FormaPagamentoRepository;
import com.rabelo.tecfood.domain.service.exception.EntidadeEmUsoException;
import com.rabelo.tecfood.domain.service.exception.EntidadeNaoEncontradaException;
import com.rabelo.tecfood.domain.service.exception.EntidadeJaCadastradaException;

@Service
public class CadastroFormaPagamentoService {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		FormaPagamento formaPagamentoAtual = formaPagamentoRepository.findByNome(formaPagamento.getNome());

		if (formaPagamentoAtual != null) {
			throw new EntidadeJaCadastradaException("Já existente !");

		}

		return formaPagamentoRepository.save(formaPagamento);
	}

	public FormaPagamento atualizarFormaPagamento(Long id, FormaPagamento formaPagamento) {

		FormaPagamento pagamentoAtual = formaPagamentoRepository.findById(id).orElse(null);

		if (pagamentoAtual != null) {

			BeanUtils.copyProperties(formaPagamento, pagamentoAtual, "id");
			FormaPagamento pagamentoSalvo = formaPagamentoRepository.save(pagamentoAtual);

			return pagamentoSalvo;
		}

		return pagamentoAtual;
	}

	public void excluirFormaPagamento(Long id) {
		FormaPagamento formaPagamento = formaPagamentoRepository.findById(id).orElse(null);

		try {
			if (formaPagamento != null) {
				formaPagamentoRepository.delete(formaPagamento);

			}

		} catch (EmptyResultDataAccessException e) {
        throw new EntidadeNaoEncontradaException("Não existe um cadastro");
        
		}catch (DataIntegrityViolationException e) {
		throw new EntidadeEmUsoException("Forma de Pagamento não pode ser removida!");
		}
	}

}
