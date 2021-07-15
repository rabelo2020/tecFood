package com.rabelo.tecfood.domain.model.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class CozinhaId {
	
	@NotNull
	private Long id;

}
