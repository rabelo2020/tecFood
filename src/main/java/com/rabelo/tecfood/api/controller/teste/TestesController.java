package com.rabelo.tecfood.api.controller.teste;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rabelo.tecfood.domain.model.Cozinha;
import com.rabelo.tecfood.domain.model.Restaurante;
import com.rabelo.tecfood.domain.repository.teste.CozinhasRepository;
import com.rabelo.tecfood.domain.repository.teste.RestaurantesRepository;

@RestController
@RequestMapping("testes")
public class TestesController {

	@Autowired
	private CozinhasRepository cozinhaRepository;

	@Autowired
	private RestaurantesRepository restaurantesRepository;

	@GetMapping("/buscar-count-cozinha")
	public int quantidadeCozinhas(Long cozinha) {
		return restaurantesRepository.countByCozinhaId(cozinha);
	}

	@GetMapping("/buscar-existe-nome")
	public boolean existerNome(String nome) {
		return cozinhaRepository.existsByNome(nome);
	}

	@GetMapping("/buscar-os-dois-primeiros-nome")
	public List<Restaurante> buscarOsDoisPrimeirosNomes(String nome) {
		return restaurantesRepository.findTop2ByNomeContaining(nome);
	}

	@GetMapping("/buscar-por-primeiro-nome")
	public Optional<Restaurante> buscarPorPrimeiroNome(String nome) {
		return restaurantesRepository.findFirstByNomeContaining(nome);
	}

	@GetMapping("/buscar-por-nome-id")
	public List<Restaurante> buscarNomePorId(String nome, Long cozinhaid) {
		return restaurantesRepository.findByNomeContainingAndCozinhaId(nome, cozinhaid);
	}

	@GetMapping("/buscar-por-taxaInicial-taxaFinal")
	public List<Restaurante> buscarRestaurantePorTaxa(BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restaurantesRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
	}

	@GetMapping("/buscar-por-nome")
	public ResponseEntity<?> buscarCozinha(@RequestParam String nome) {

		List<Cozinha> cozinha = cozinhaRepository.findByNomeContaining(nome);

		if (cozinha == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(cozinha);

	}
}
