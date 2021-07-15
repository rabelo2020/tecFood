package com.rabelo.tecfood.domain.model.mix;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rabelo.tecfood.domain.model.Estado;

public abstract class CidadeMix {
	
	//Somente Desereliazação Json para Obj
	 @JsonIgnoreProperties(value = "nome", allowGetters = true)
	 private Estado estado;

}
