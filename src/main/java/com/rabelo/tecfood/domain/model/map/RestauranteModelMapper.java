package com.rabelo.tecfood.domain.model.map;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabelo.tecfood.domain.model.Cozinha;
import com.rabelo.tecfood.domain.model.Restaurante;
import com.rabelo.tecfood.domain.model.request.RestauranteRequest;
import com.rabelo.tecfood.domain.model.response.RestauranteResponse;

@Component
public class RestauranteModelMapper {
	
	@Autowired
	private ModelMapper mapper;
	
	public Restaurante toRestaurante(RestauranteRequest restauranteRequest) {
		
		return mapper.map(restauranteRequest, Restaurante.class);
	}
	
	public RestauranteResponse toRestauranteResponse(Restaurante restaurante) {
		return mapper.map(restaurante, RestauranteResponse.class);
	}
	
	public List<RestauranteResponse> toCollectionRestaurantes(List<Restaurante> restaurantes){
		return restaurantes.stream()
				           .map(x -> toRestauranteResponse(x))
				           .collect(Collectors.toList());
	}
	
	public void copyToDomainObject(RestauranteRequest restauranteRequest, Restaurante restaurante) {
		restaurante.setCozinha(new Cozinha());
		mapper.map(restauranteRequest, restaurante);
	}

}
