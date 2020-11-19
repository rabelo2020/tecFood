package com.rabelo.tecfood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import com.rabelo.tecfood.domain.model.Estado;
import com.rabelo.tecfood.domain.repository.EstadoRepository;
import com.rabelo.tecfood.domain.service.CadastroCozinhaService;
import com.rabelo.tecfood.domain.service.CadastroEstadoService;
import com.rabelo.tecfood.domain.service.exception.ItemExistenteException;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CadastroEstadoService cadastroEstadoService;

	@GetMapping
	public List<Estado> lista() {
		return estadoRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Estado> buscarEstado(@PathVariable Long id) {

		Optional<Estado> estadoId = estadoRepository.findById(id);

		if (estadoId.isPresent()) {
			return ResponseEntity.ok(estadoId.get());
		}

		return ResponseEntity.notFound().build();

	}

	@PostMapping
	public ResponseEntity<Estado> salvar(@RequestBody Estado estado) {

		try {

			Estado estadoSalvo = cadastroEstadoService.salvar(estado);
			return ResponseEntity.status(HttpStatus.CREATED).body(estadoSalvo);

		} catch (ItemExistenteException e) {
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Estado> atualizar(@PathVariable Long id, @RequestBody Estado estado) {

		Optional<Estado> estadoAtual = estadoRepository.findById(id);

		if (estadoAtual.isPresent()) {
			BeanUtils.copyProperties(estado, estadoAtual.get(), "id");
			Estado estadoSalvo = estadoRepository.save(estadoAtual.get());

			return ResponseEntity.ok(estadoSalvo);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Estado> remover(@PathVariable Long id) {
		Optional<Estado> estadoId = estadoRepository.findById(id);

		try {
			if (estadoId.isPresent()) {
				estadoRepository.deleteById(estadoId.get().getId());
				return ResponseEntity.noContent().build();
			}

			return ResponseEntity.notFound().build();

		} catch (DataIntegrityViolationException e) {

			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
