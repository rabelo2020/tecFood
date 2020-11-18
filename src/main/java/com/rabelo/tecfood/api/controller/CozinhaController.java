package com.rabelo.tecfood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping(value = "/cozinhas", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@GetMapping()
	public List<Cozinha> listar() {
		return cozinhaRepository.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cozinha> buscarCozinha(@PathVariable Long id) {
		Cozinha cozinha = cozinhaRepository.buscarCozinha(id);

		return cozinha != null ? ResponseEntity.ok(cozinha) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public Cozinha salvar(@RequestBody Cozinha cozinha) {
		Cozinha cozinhasalva = cozinhaRepository.salvarCozinha(cozinha);
		return cozinhasalva;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha) {

		Cozinha cozinhaAtual = cozinhaRepository.buscarCozinha(id);

		if (cozinhaAtual != null) {

			BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
			Cozinha cozinhaSalva = cozinhaRepository.salvarCozinha(cozinhaAtual);

			return ResponseEntity.ok(cozinhaSalva);
		}

		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Cozinha> remover(@PathVariable Long id) {
		Cozinha cozinhaId = cozinhaRepository.buscarCozinha(id);

		if (cozinhaId != null) {
			cozinhaRepository.remover(cozinhaId.getId());

			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}

}
