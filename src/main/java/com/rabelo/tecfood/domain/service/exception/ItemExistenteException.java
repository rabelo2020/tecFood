package com.rabelo.tecfood.domain.service.exception;

public class ItemExistenteException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	String msg;
	
	public ItemExistenteException(String msg) {
		this.msg = msg;
	}
	
	

}
