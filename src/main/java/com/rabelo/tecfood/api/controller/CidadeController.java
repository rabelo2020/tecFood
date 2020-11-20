package com.rabelo.tecfood.api.controller;

import java.util.List;

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

import com.rabelo.tecfood.domain.model.Cidade;
import com.rabelo.tecfood.domain.repository.CidadeRepository;
import com.rabelo.tecfood.domain.service.CadastroCidadeService;
import com.rabelo.tecfood.domain.service.exception.EntidadeEmUsoException;
import com.rabelo.tecfood.domain.service.exception.EntidadeNaoEncontradaException;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CadastroCidadeService cadastroCidadeService;

	@GetMapping
	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cidade> buscarCidade(@PathVariable Long id) {
		Cidade cidadeAtual = cidadeRepository.findById(id).orElse(null);

		return cidadeAtual != null ? ResponseEntity.ok(cidadeAtual) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Cidade> salvar(@RequestBody Cidade cidade) {

		try {
			Cidade cidadeAtual = cadastroCidadeService.salvar(cidade);
			return ResponseEntity.status(HttpStatus.CREATED).body(cidadeAtual);

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cidade> atualizar(@PathVariable Long id, @RequestBody Cidade cidade) {
		try {
			Cidade cidadeAtual = cadastroCidadeService.atualizar(id, cidade);

			return ResponseEntity.ok(cidadeAtual);

		} catch (EntidadeNaoEncontradaException e) {

			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Cidade> remover(@PathVariable Long id) {
		Cidade cidade = cidadeRepository.findById(id).orElse(null);

		try {
			if (cidade != null) {
				cidadeRepository.delete(cidade);
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}
