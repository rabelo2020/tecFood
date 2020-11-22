package com.rabelo.tecfood.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rabelo.tecfood.domain.model.Restaurante;
import com.rabelo.tecfood.domain.repository.RestauranteRepository;
import com.rabelo.tecfood.domain.service.CadastroRestauranteService;
import com.rabelo.tecfood.domain.service.exception.EntidadeJaCadastradaException;
import com.rabelo.tecfood.domain.service.exception.EntidadeNaoEncontradaException;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;

	@GetMapping
	public List<Restaurante> listar() {
		return restauranteRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Restaurante> buscarRestaurante(@PathVariable Long id) {

		Restaurante restauranteId = restauranteRepository.findById(id).orElse(null);

		return restauranteId != null ? ResponseEntity.ok(restauranteId)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Restaurante restaurante) {

		try {
			Restaurante restauranteSalvo = cadastroRestauranteService.salvar(restaurante);
			return ResponseEntity.status(HttpStatus.CREATED).body(restauranteSalvo);

		} catch (EntidadeJaCadastradaException e) {

			return ResponseEntity.badRequest().body(e.getMessage());

		} catch (EntidadeNaoEncontradaException e) {

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Restaurante> atualizar(@PathVariable Long id, @RequestBody Restaurante restauranteClient) {

		try {
			Restaurante restauranteAtual = cadastroRestauranteService.atualizar(id, restauranteClient);

			if (restauranteAtual != null) {

				return ResponseEntity.ok(restauranteAtual);
			}
			return ResponseEntity.notFound().build();

		} catch (EntidadeJaCadastradaException e) {

			return ResponseEntity.status(HttpStatus.CONFLICT).build();

		} catch (EntidadeNaoEncontradaException e) {

			return ResponseEntity.noContent().build();
		}

	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long id,
			@RequestBody Map<String, Object> camposRestaurante) {

		Restaurante restauranteAtual = restauranteRepository.findById(id).orElse(null);
		
		if (restauranteAtual == null) {
			return ResponseEntity.notFound().build();
		}
		camposRestaurante.forEach((nomeCampo, valorCampo) -> {
			System.out.println(nomeCampo + ": " + valorCampo);
			
			merge(camposRestaurante, restauranteAtual);
		});
		return ResponseEntity.ok().build();

	}

	private void merge(Map<String, Object> camposRestaurante, Restaurante restauranteAtual) {
		// TODO Auto-generated method stub
		
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Restaurante> remover(@PathVariable Long id) {
		Restaurante restauranteId = restauranteRepository.findById(id).orElse(null);

		if (restauranteId != null) {
			restauranteRepository.delete(restauranteId);

			return ResponseEntity.noContent().build();

		}

		return ResponseEntity.notFound().build();
	}

}
