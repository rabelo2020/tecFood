package com.rabelo.tecfood.api.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import com.rabelo.tecfood.domain.model.Cozinha;
import com.rabelo.tecfood.domain.repository.CozinhaRepository;
import com.rabelo.tecfood.domain.service.CadastroCozinhaService;
import com.rabelo.tecfood.domain.service.exception.EntidadeJaCadastradaException;

@RestController
@RequestMapping(value = "/cozinhas",
produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
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
	public ResponseEntity<?> buscarCozinha(@PathVariable Long id) {
		Cozinha cozinha = cozinhaRepository.findById(id).orElse(null);

		if (cozinha != null) {
			
		return ResponseEntity.ok(cozinha);
		}
		
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Cozinha cozinha) {

		try {
			Cozinha cozinhasalva = cadastrocozinhaService.salva(cozinha);

			return ResponseEntity.status(HttpStatus.CREATED).body(cozinhasalva);

		} catch (EntidadeJaCadastradaException e) {

			return ResponseEntity.badRequest().body(e.getLocalizedMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha) {

		Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(id);

		if (cozinhaAtual.isPresent()) {

			BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");
			Cozinha cozinhaSalva = cozinhaRepository.save(cozinhaAtual.get());

			return ResponseEntity.ok(cozinhaSalva);
		}

		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Cozinha> remover(@PathVariable Long id) {
		Optional<Cozinha> cozinhaId = cozinhaRepository.findById(id);

		try {
			if (cozinhaId.get() != null) {
				cozinhaRepository.deleteById(cozinhaId.get().getId());

				return ResponseEntity.noContent().build();
			}

			return ResponseEntity.notFound().build();
			
		} catch (DataIntegrityViolationException e) {
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}
