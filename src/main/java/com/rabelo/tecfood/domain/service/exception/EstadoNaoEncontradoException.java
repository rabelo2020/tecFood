package com.rabelo.tecfood.domain.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	private static final String MSG_ESTADO_NAO_CADASTRADA = "Não existe um cadastro de estado com código %d";

	public EstadoNaoEncontradoException(String msg) {
		super(msg);
	}
  public EstadoNaoEncontradoException(Long id) {
this(String.format(MSG_ESTADO_NAO_CADASTRADA, id));
}
}
