package com.rabelo.tecfood.domain.service.exception;

//@ResponseStatus(HttpStatus.CONFLICT)
public class EntidadeEmUsoException extends NegocioException{

	private static final long serialVersionUID = 1L;
	
	public EntidadeEmUsoException(String msg) {
		super(msg);
	}

}
