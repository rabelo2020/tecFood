package com.rabelo.tecfood.domain.model.response;

import java.math.BigDecimal;

import com.rabelo.tecfood.domain.model.response.CozinhaResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteResponse {
	
	private Long id;
	private BigDecimal taxaFrete;
	private String nome;
	private CozinhaResponse cozinha;

}
