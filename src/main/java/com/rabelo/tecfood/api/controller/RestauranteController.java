package com.rabelo.tecfood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabelo.tecfood.domain.model.Restaurante;
import com.rabelo.tecfood.domain.repository.RestauranteRepository;
import com.rabelo.tecfood.domain.service.CadastroRestauranteService;
import com.rabelo.tecfood.domain.service.exception.CozinhaNaoEncontradaException;
import com.rabelo.tecfood.domain.service.exception.NegocioException;

@RestController
@RequestMapping(value = "/restaurantes"
               , produces = {MediaType.APPLICATION_JSON_VALUE})
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
	public Restaurante buscarRestaurante(@PathVariable Long id) {
		return cadastroRestauranteService.buscarOuFalhar(id);

		/*
		 * Restaurante restauranteId = restauranteRepository.findById(id).orElse(null);
		 * 
		 * return restauranteId != null ? ResponseEntity.ok(restauranteId) :
		 * ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		 */	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante salvar(@RequestBody @Valid Restaurante restaurante) {
		
		try {
			return cadastroRestauranteService.salvar(restaurante);
			
		} catch (CozinhaNaoEncontradaException e) {
			
			throw new NegocioException(e.getMessage());
		}
		/*
		 * try { Restaurante restauranteSalvo =
		 * cadastroRestauranteService.salvar(restaurante); return
		 * ResponseEntity.status(HttpStatus.CREATED).body(restauranteSalvo);
		 * 
		 * } catch (EntidadeJaCadastradaException e) {
		 * 
		 * return ResponseEntity.badRequest().body(e.getMessage());
		 * 
		 * } catch (EntidadeNaoEncontradaException e) {
		 * 
		 * return ResponseEntity.badRequest().body(e.getMessage()); }
		 */
		
	}

	@PatchMapping("/{id}")
	public Restaurante atualizarParcial(@PathVariable Long id,
			@RequestBody Map<String, Object> camposRestaurante, HttpServletRequest request) {

		Restaurante restauranteAtual = cadastroRestauranteService.buscarOuFalhar(id);

		merge(camposRestaurante, restauranteAtual, request);
		return atualizar(id, restauranteAtual);
	}


	@PutMapping("/{id}")
	public Restaurante atualizar(@PathVariable Long id, @RequestBody @Valid Restaurante restauranteClient) {

		try {
			 Restaurante restauranteAtual = cadastroRestauranteService.buscarOuFalhar(id);
						
			 BeanUtils.copyProperties(restauranteClient, restauranteAtual, 
				"id", "formasPagamento", "endereco", "dataCadastro", "produtos");

				return cadastroRestauranteService.salvar(restauranteAtual);
			  } catch (CozinhaNaoEncontradaException e) {
				  
				throw new NegocioException(e.getMessage());
			  }
		/*
		 * try { Restaurante restauranteAtual = cadastroRestauranteService.atualizar(id,
		 * restauranteClient);
		 * 
		 * if (restauranteAtual != null) {
		 * 
		 * return ResponseEntity.ok(restauranteAtual); } return
		 * ResponseEntity.notFound().build();
		 * 
		 * } catch (EntidadeJaCadastradaException e) {
		 * 
		 * return ResponseEntity.status(HttpStatus.CONFLICT).build();
		 * 
		 * } catch (EntidadeNaoEncontradaException e) {
		 * 
		 * return ResponseEntity.noContent().build(); }
		 */

	}

	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id) {
		

cadastroRestauranteService.excluir(id);
		/*
		 * Restaurante restauranteId = restauranteRepository.findById(id).orElse(null);
		 * if (restauranteId != null) { restauranteRepository.delete(restauranteId);
		 * 
		 * return ResponseEntity.noContent().build();
		 * 
		 * }
		 * 
		 * return ResponseEntity.notFound().build();
		 */	}
	

	private void merge(Map<String, Object> camposRestaurante, Restaurante restauranteDestino, HttpServletRequest request) {
		ServletServerHttpRequest httpRequest = new ServletServerHttpRequest(request);
		try {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

		//Converter todas as propiedades camposRestaurante, no tipo de cada campo de Restaurante.class 
				Restaurante restauranteOrigem = objectMapper.convertValue(camposRestaurante, Restaurante.class);
				
				camposRestaurante.forEach((nomeCampo, valorCampo) -> {
					
					/* Busca o nome da Propiedade em nomeCampo */
				Field field = ReflectionUtils.findField(Restaurante.class, nomeCampo);
					/* Habilitando o field "campo" q está como private para, acessivel */
				field.setAccessible(true);
				
		/* Buscar ou  faz um "get" em restauranteOrigem, referente ao valor do field, e setar o valor no campo field */
				Object novoValorCampo = ReflectionUtils.getField(field, restauranteOrigem);			
					
					//  Atribui um valor na Propiedade campo "field", no objeto, restauranteDestino o valor "novoValorCampo" 
					 ReflectionUtils.setField(field, restauranteDestino, novoValorCampo);			
			
		});
		}catch (IllegalArgumentException e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, httpRequest);
		}
	}

}
