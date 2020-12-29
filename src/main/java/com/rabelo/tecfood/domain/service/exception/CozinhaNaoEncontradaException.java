package com.rabelo.tecfood.domain.service.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	private static final String MSG_COZINHA_NAO_EXISTE = "Não existe um cadastro de Cozinha com código %d";

	public CozinhaNaoEncontradaException(String msg) {
		super(msg);

	}
	
	public CozinhaNaoEncontradaException(Long id) {
this(String.format(MSG_COZINHA_NAO_EXISTE, id));
	}

}
