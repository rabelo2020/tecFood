package com.rabelo.tecfood.domain.model.mix;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rabelo.tecfood.domain.model.Restaurante;

public abstract class CozinhaMix {

	@JsonIgnore
	private List<Restaurante> restaurantes = new ArrayList<>(); 
}
