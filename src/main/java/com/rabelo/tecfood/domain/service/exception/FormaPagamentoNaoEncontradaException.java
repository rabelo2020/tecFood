package com.rabelo.tecfood.domain.service.exception;

public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public FormaPagamentoNaoEncontradaException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	
	public FormaPagamentoNaoEncontradaException(Long id) {
this(String.format("Não existe um cadastro de Forma de Pagamento com código %d", id));
	}

}
