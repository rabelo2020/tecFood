package com.rabelo.tecfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabelo.tecfood.domain.model.Estado;
import com.rabelo.tecfood.domain.repository.EstadoRepository;

@RestController
@RequestMapping("/estados")
public class EstadoController {
		
	@Autowired
	private EstadoRepository estadoRepository;
	
	@GetMapping
	public List<Estado> lista(){
		return estadoRepository.findAll();
	}

}
