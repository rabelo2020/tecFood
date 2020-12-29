package com.rabelo.tecfood.domain.service.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public ProdutoNaoEncontradoException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	
	public ProdutoNaoEncontradoException(Long id) {
this(String.format("Não existe um cadastro de produto com código %d", id));
	}

}
