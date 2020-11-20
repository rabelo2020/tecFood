package com.rabelo.tecfood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabelo.tecfood.domain.model.FormaPagamento;
import com.rabelo.tecfood.domain.repository.FormaPagamentoRepository;
import com.rabelo.tecfood.domain.service.CadastroFormaPagamentoService;
import com.rabelo.tecfood.domain.service.exception.EntidadeEmUsoException;
import com.rabelo.tecfood.domain.service.exception.EntidadeNaoEncontradaException;
import com.rabelo.tecfood.domain.service.exception.EntidadeJaCadastradaException;

@RestController
@RequestMapping("/formaPagamentos")
public class FormaPagamentoController {

	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamentoService;

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	@GetMapping
	public List<FormaPagamento> listar() {
		return formaPagamentoRepository.findAll();

	}

	@GetMapping("/{id}")
	public ResponseEntity<FormaPagamento> buscarFormaPagamento(@PathVariable Long id) {

		Optional<FormaPagamento> formaPagamento = formaPagamentoRepository.findById(id);

		return formaPagamento.isPresent() ? ResponseEntity.ok(formaPagamento.get()) : ResponseEntity.notFound().build();

	}

	@PostMapping
	public ResponseEntity<FormaPagamento> salvar(@RequestBody FormaPagamento formaPagamento) {
		try {
			FormaPagamento pagamento = cadastroFormaPagamentoService.salvar(formaPagamento);
			return ResponseEntity.status(HttpStatus.CREATED).body(pagamento);

		} catch (EntidadeJaCadastradaException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<FormaPagamento> atualizar(@PathVariable Long id, @RequestBody FormaPagamento formaPagamento) {

		FormaPagamento pagamento = cadastroFormaPagamentoService.atualizarFormaPagamento(id, formaPagamento);

		return pagamento != null ? ResponseEntity.ok(pagamento) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<FormaPagamento> remover(@PathVariable Long id) {

		try {
			cadastroFormaPagamentoService.excluirFormaPagamento(id);
			return ResponseEntity.noContent().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();

		}
	}

}
