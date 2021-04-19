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
import com.rabelo.tecfood.domain.repository.RestauranteRepository;
import com.rabelo.tecfood.domain.repository.teste.CozinhasRepository;

@RestController
@RequestMapping("testes")
public class TestesController {

	
	@Autowired
	private CozinhasRepository cozinhaRepository;

	@Autowired
	private RestauranteRepository restaurantesRepository;
	
	@GetMapping("/buscar-primeiro-cozinha")
	public Optional<Cozinha> buscarUnicaCozinha() {

		return cozinhaRepository.buscarPrimeiro();
	}
	
	@GetMapping("/buscar-primeiro-restaurante")
	public Optional<Restaurante> buscarUnicoRestaurante() {

		return restaurantesRepository.buscarPrimeiro();
	}
	//UPPER(name) =UPPER(name)

	@GetMapping("/buscar-com-frete-gratis")
	public List<Restaurante> restauranteComFreteGratis(String nome) {

		return restaurantesRepository.findComFreteGratis(nome);
	}

	@GetMapping("/buscar-jpql-nome-taxaInicial-taxaFinal")
	public List<Restaurante> buscarNomeTaxaInicialTaxaFinal(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restaurantesRepository.find(nome, taxaInicial, taxaFinal);
	}

	@GetMapping("/buscar-por-nome-query")
	public List<Restaurante> buscarNomeQuery(String nome, Long id) {
		return restaurantesRepository.consultarPorNome(nome, id);
	}

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
