package com.rabelo.tecfood.domain.service.exception;

public class EntidadeJaCadastradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	String msg;
	
	public EntidadeJaCadastradaException(String msg) {
		this.msg = msg;
	}
	
	

}
