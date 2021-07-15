package com.rabelo.tecfood.core.modelConfig;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabelo.tecfood.domain.model.Restaurante;
import com.rabelo.tecfood.domain.model.request.RestauranteRequest;

@Configuration
public class ModelConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		
		/*		var modelMapper = new ModelMapper();

		 * modelMapper.createTypeMap(RestauranteRequest.class, Restaurante.class)
		 * .addMapping(RestauranteRequest::getCozinhaId, Restaurante::getCozinha);
		 */
		return new ModelMapper();
	}

}
