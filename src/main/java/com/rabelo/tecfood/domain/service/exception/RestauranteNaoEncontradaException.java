package com.rabelo.tecfood.domain.service.exception;

public class RestauranteNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	private static final String MSG_RESTAURANTE_NAO_EXISTE = "Não existe um cadastro de cidade com código %d";

	public RestauranteNaoEncontradaException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	
	public RestauranteNaoEncontradaException(Long id) {
this(String.format(MSG_RESTAURANTE_NAO_EXISTE, id));
	}

}
