package com.rabelo.tecfood.domain.service.exception;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public abstract class EntidadeNaoEncontradaException extends NegocioException {

	private static final long serialVersionUID = 1L;
	// private String msg;

	/*
	 * public EntidadeNaoEncontradaException(HttpStatus status, String msg) {
	 * super(status, msg);
	 * 
	 * }
	 * 
	 * public EntidadeNaoEncontradaException(String msg){ this(HttpStatus.NOT_FOUND,
	 * msg); }
	 */

	public EntidadeNaoEncontradaException(String msg) {
		super(msg);
	}

}
