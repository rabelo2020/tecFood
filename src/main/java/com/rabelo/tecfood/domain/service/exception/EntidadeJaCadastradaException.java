package com.rabelo.tecfood.domain.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntidadeJaCadastradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	
	
	public EntidadeJaCadastradaException(String msg) {
		super(msg);
	}
	
	

}
