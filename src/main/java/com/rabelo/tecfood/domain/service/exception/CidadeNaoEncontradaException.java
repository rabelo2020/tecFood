package com.rabelo.tecfood.domain.service.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

		private static final long serialVersionUID = 1L;
		private static final String MSG_CIDADE_NAO_ENCOTRADA = "Não existe um cadastro de cidade com código %d";
		
		public CidadeNaoEncontradaException(String msg) {
			super(msg);
			
			}
		
		public CidadeNaoEncontradaException(Long id) {
		this(String.format(MSG_CIDADE_NAO_ENCOTRADA, id));
		}

}
