package com.rabelo.tecfood.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.rabelo.tecfood.domain.model.Cozinha;
import com.rabelo.tecfood.domain.repository.CozinhaRepository;
import com.rabelo.tecfood.domain.service.CadastroCozinhaService;
import com.rabelo.tecfood.domain.service.exception.EntidadeJaCadastradaException;
import com.rabelo.tecfood.domain.service.exception.EntidadeNaoEncontradaException;

@RestController
@RequestMapping(value = "/cozinhas")
//,produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CadastroCozinhaService cadastrocozinhaService;

	@GetMapping()
	public List<Cozinha> listar() {
		return cozinhaRepository.findAll();
	}

	@GetMapping("/{id}")
	public Cozinha buscarCozinha(@PathVariable Long id) {
		
		return cadastrocozinhaService.buscarOuFalhar(id);
		
		/*
		 * Cozinha cozinha = cozinhaRepository.findById(id).orElse(null);
		 * 
		 * if (cozinha != null) {
		 * 
		 * return ResponseEntity.ok(cozinha); }
		 * 
		 * return ResponseEntity.notFound().build();
		 */	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha salvar(@RequestBody @Valid Cozinha cozinha) {
		return cadastrocozinhaService.salva(cozinha);

		/*
		 * try { Cozinha cozinhasalva = cadastrocozinhaService.salva(cozinha);
		 * 
		 * return ResponseEntity.status(HttpStatus.CREATED).body(cozinhasalva);
		 * 
		 * } catch (EntidadeJaCadastradaException e) {
		 * 
		 * return ResponseEntity.badRequest().body(e.getLocalizedMessage()); }
		 */	}

	@PutMapping("/{id}")
	public Cozinha atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha) {		
		
		Cozinha cozinhaAtual = cadastrocozinhaService.buscarOuFalhar(id);
		
		BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
		return cozinhaRepository.save(cozinhaAtual);

		/*
		 * Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(id);
		 * 
		 * if (cozinhaAtual.isPresent()) {
		 * 
		 * BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id"); Cozinha
		 * cozinhaSalva = cozinhaRepository.save(cozinhaAtual.get());
		 * 
		 * return ResponseEntity.ok(cozinhaSalva); }
		 * 
		 * return ResponseEntity.notFound().build();
		 */
	}

	/*
	 * @DeleteMapping("/{id}") public ResponseEntity<Cozinha> remover(@PathVariable
	 * Long id) { Optional<Cozinha> cozinhaId = cozinhaRepository.findById(id);
	 * 
	 * try { if (cozinhaId.get() != null) {
	 * cozinhaRepository.deleteById(cozinhaId.get().getId());
	 * 
	 * return ResponseEntity.noContent().build(); }
	 * 
	 * return ResponseEntity.notFound().build();
	 * 
	 * } catch (DataIntegrityViolationException e) {
	 * 
	 * return ResponseEntity.status(HttpStatus.CONFLICT).build(); } }
	 */
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		
	cadastrocozinhaService.excluir(id);
	}

}
