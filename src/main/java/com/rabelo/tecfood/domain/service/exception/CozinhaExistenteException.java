package com.rabelo.tecfood.domain.service.exception;

public class CozinhaExistenteException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	String msg;
	
	public CozinhaExistenteException(String msg) {
		this.msg = msg;
	}
	
	

}