package com.rabelo.tecfood.domain.model.mix;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rabelo.tecfood.domain.model.Cozinha;
import com.rabelo.tecfood.domain.model.Endereco;
import com.rabelo.tecfood.domain.model.FormaPagamento;
import com.rabelo.tecfood.domain.model.Produto;

public abstract class RestauranteMixin {
		
	// @JsonIgnore
	@JsonIgnoreProperties(value = "nome", allowGetters = true)//Somente Desereliazação Json para Obj
	private Cozinha cozinha;

	//@JsonIgnore
	private OffsetDateTime dataCadastro;

	//@JsonIgnore
	private OffsetDateTime dataAtualizacao;

	@JsonIgnore
	private Endereco endereco;

	@JsonIgnore
	private List<Produto> produtos = new ArrayList<>();

	@JsonIgnore
	private List<FormaPagamento> formaPagamento = new ArrayList<>();


}
