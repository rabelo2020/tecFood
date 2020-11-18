package com.rabelo.tecfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabelo.tecfood.domain.model.Cozinha;
import com.rabelo.tecfood.domain.repository.CozinhaRepository;
import com.rabelo.tecfood.domain.service.exception.CozinhaExistenteException;

@Service
public class CadastroCozinhaService {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Cozinha salva(Cozinha cozinha) {
		Cozinha cozinhaAtual = cozinhaRepository.findByNome(cozinha.getNome());
		
		if (cozinhaAtual != null) {
			
			throw new CozinhaExistenteException("Cozinha já existente no BD!");
		}		
		return cozinhaRepository.save(cozinha);
		
	}



}
