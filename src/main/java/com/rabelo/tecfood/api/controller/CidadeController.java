package com.rabelo.tecfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabelo.tecfood.domain.model.Cidade;
import com.rabelo.tecfood.domain.repository.CidadeRepository;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@GetMapping
	public List<Cidade> listar(){
		return cidadeRepository.findAll();
	}

}
