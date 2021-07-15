package com.rabelo.tecfood.core.jackson;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.rabelo.tecfood.domain.model.Cidade;
import com.rabelo.tecfood.domain.model.Cozinha;
import com.rabelo.tecfood.domain.model.Restaurante;
import com.rabelo.tecfood.domain.model.mix.CidadeMix;
import com.rabelo.tecfood.domain.model.mix.CozinhaMix;
import com.rabelo.tecfood.domain.model.mix.RestauranteMixin;

//@Component
public class JacksonMixinModel extends SimpleModule{

	private static final long serialVersionUID = 1L;
	
	/*
	 * public JacksonMixinModel() { setMixInAnnotation(Restaurante.class,
	 * RestauranteMixin.class); setMixInAnnotation(Cozinha.class, CozinhaMix.class);
	 * setMixInAnnotation(Cidade.class, CidadeMix.class); }
	 */

}
