package com.rabelo.tecfood.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rabelo.tecfood.api.exceptionhandler.Problem;
import com.rabelo.tecfood.domain.model.Cidade;
import com.rabelo.tecfood.domain.repository.CidadeRepository;
import com.rabelo.tecfood.domain.service.CadastroCidadeService;
import com.rabelo.tecfood.domain.service.exception.CidadeNaoEncontradaException;
import com.rabelo.tecfood.domain.service.exception.EntidadeNaoEncontradaException;
import com.rabelo.tecfood.domain.service.exception.EstadoNaoEncontradoException;
import com.rabelo.tecfood.domain.service.exception.NegocioException;

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
	public Cidade buscarCidade(@PathVariable Long id) {
		
		return cadastroCidadeService.buscarOuFalhar(id);
		/*
		 * Cidade cidadeAtual = cidadeRepository.findById(id).orElse(null); return
		 * cidadeAtual != null ? ResponseEntity.ok(cidadeAtual) :
		 * ResponseEntity.notFound().build();
		 */
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade salvar(@RequestBody @Valid Cidade cidade) {
		return cadastroCidadeService.salvar(cidade);

		/*
		 * try { Cidade cidadeAtual = cadastroCidadeService.salvar(cidade); return
		 * ResponseEntity.status(HttpStatus.CREATED).body(cidadeAtual);
		 * 
		 * } catch (EntidadeNaoEncontradaException e) { return
		 * ResponseEntity.notFound().build(); }
		 */	}

	@PutMapping("/{id}")
	public Cidade atualizar(@PathVariable Long id, @RequestBody @Valid Cidade cidade) {
		
		Cidade cidadeAtual = cadastroCidadeService.buscarOuFalhar(id);
		
		BeanUtils.copyProperties(cidade, cidadeAtual, "id");
		
		return cadastroCidadeService.salvar(cidadeAtual);
		
		
		/*
		 * try { Cidade cidadeAtual = cadastroCidadeService.atualizar(id, cidade);
		 * 
		 * return ResponseEntity.ok(cidadeAtual);
		 * 
		 * } catch (EntidadeNaoEncontradaException e) {
		 * 
		 * return ResponseEntity.notFound().build(); }
		 */	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		cadastroCidadeService.excluir(id);
		/*
		 * Cidade cidade = cidadeRepository.findById(id).orElse(null); try { if (cidade
		 * != null) { cidadeRepository.delete(cidade); return
		 * ResponseEntity.noContent().build(); } return
		 * ResponseEntity.notFound().build();
		 * 
		 * } catch (EntidadeEmUsoException e) { return
		 * ResponseEntity.status(HttpStatus.CONFLICT).build(); }
		 */	}
	


}
