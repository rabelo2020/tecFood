package com.rabelo.tecfood.domain.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rabelo.tecfood.domain.model.FormaPagamento;
import com.rabelo.tecfood.domain.repository.FormaPagamentoRepository;
import com.rabelo.tecfood.domain.service.exception.EntidadeEmUsoException;
import com.rabelo.tecfood.domain.service.exception.EntidadeNaoEncontradaException;
import com.rabelo.tecfood.domain.service.exception.FormaPagamentoNaoEncontradaException;
import com.rabelo.tecfood.domain.service.exception.EntidadeJaCadastradaException;

@Service
public class CadastroFormaPagamentoService {

	private static final String FORMA_DE_PAGAMENTO_ESTA_SENDO_UTILIZADA = "Forma de Pagamento %s, está sendo Utilizada !";
	private static final String FORMA_DE_PAGAMENTO_NAO_EXISTE = "Forma de Pagamento de código %d não existe!";
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	@Transactional
	public FormaPagamento salvar(FormaPagamento formaPagamento) {

		existeFormaPagamento(formaPagamento.getNome());
		return formaPagamentoRepository.save(formaPagamento);

		/*
		 * FormaPagamento formaPagamentoAtual =
		 * formaPagamentoRepository.findByNome(formaPagamento.getNome()); if
		 * (formaPagamentoAtual != null) { throw new
		 * EntidadeJaCadastradaException("Já existente !"); } return
		 * formaPagamentoRepository.save(formaPagamento);
		 */
	}

	private void existeFormaPagamento(String nome) {
		boolean pagamento = formaPagamentoRepository.existsByNome(nome);
		if (pagamento) {
			throw new EntidadeJaCadastradaException(
					String.format("Forma de Pagamento, %s já Cadastrado !", nome.toUpperCase()));
		}

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

	@Transactional
	public void excluir(Long id) {
		FormaPagamento formaPagamento = buscarOuFalhar(id);
		String nome = formaPagamento.getNome();

		try {

			formaPagamentoRepository.delete(formaPagamento);

		} catch (DataIntegrityViolationException e) {

			throw new EntidadeEmUsoException(String.format(FORMA_DE_PAGAMENTO_ESTA_SENDO_UTILIZADA, nome));

		}

		/*
		 * FormaPagamento formaPagamento =
		 * formaPagamentoRepository.findById(id).orElse(null);
		 * 
		 * try { if (formaPagamento != null) {
		 * formaPagamentoRepository.delete(formaPagamento);
		 * 
		 * }
		 * 
		 * } catch (EmptyResultDataAccessException e) { throw new
		 * EntidadeNaoEncontradaException(String.format(FORMA_DE_PAGAMENTO_NAO_EXISTE,
		 * formaPagamento.getId()));
		 * 
		 * }catch (DataIntegrityViolationException e) { throw new
		 * EntidadeEmUsoException("Forma de Pagamento não pode ser removida!"); }
		 */
	}

	public FormaPagamento buscarOuFalhar(Long id) {

		return formaPagamentoRepository.findById(id).orElseThrow(
				() -> new FormaPagamentoNaoEncontradaException(String.format(FORMA_DE_PAGAMENTO_NAO_EXISTE, id)));
	}

}
