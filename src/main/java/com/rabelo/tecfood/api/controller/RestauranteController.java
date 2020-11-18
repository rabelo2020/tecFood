package com.rabelo.tecfood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rabelo.tecfood.domain.model.Cozinha;
import com.rabelo.tecfood.domain.model.Restaurante;
import com.rabelo.tecfood.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@GetMapping
	public List<Restaurante> listar() {
		return restauranteRepository.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Restaurante> buscarRestaurante(@PathVariable Long id) {

		Restaurante restauranteId = restauranteRepository.buscarRestaurante(id);

		return restauranteId != null ? ResponseEntity.ok(restauranteId)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante salvar(@RequestBody Restaurante restaurante) {
		return restauranteRepository.salvarRestaurante(restaurante);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Restaurante> atualizar(@PathVariable Long id, @RequestBody Restaurante restauranteClient) {
		Restaurante restauranteAtual = restauranteRepository.buscarRestaurante(id);

		if (restauranteAtual != null) {
			BeanUtils.copyProperties(restauranteClient, restauranteAtual, "id");
			Restaurante restauranteSalvo = restauranteRepository.salvarRestaurante(restauranteAtual);
			return ResponseEntity.ok(restauranteSalvo);
		}

		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Restaurante> remover(@PathVariable Long id) {
		Restaurante restauranteId = restauranteRepository.buscarRestaurante(id);

		if (restauranteId != null) {
			restauranteRepository.remover(restauranteId.getId());
			return ResponseEntity.noContent().build();

		}

		return ResponseEntity.notFound().build();
	}

}
