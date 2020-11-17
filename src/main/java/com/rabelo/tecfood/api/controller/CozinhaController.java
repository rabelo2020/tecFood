package com.rabelo.tecfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rabelo.tecfood.domain.model.Cozinha;
import com.rabelo.tecfood.domain.repository.CozinhaRepository;

@RestController
@RequestMapping(value = "/cozinhas", 
produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@JsonIgnore
	@GetMapping()
	public List<Cozinha> listar(){
		return cozinhaRepository.listar();
	}
	
	@JsonIgnore
	@GetMapping("/{id}")
	public Cozinha buscarCozinha(@PathVariable Long id) {
		return cozinhaRepository.buscarCozinha(id);
		
	}

}
