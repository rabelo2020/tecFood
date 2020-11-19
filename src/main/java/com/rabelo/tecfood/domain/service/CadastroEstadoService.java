package com.rabelo.tecfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabelo.tecfood.domain.model.Estado;
import com.rabelo.tecfood.domain.repository.EstadoRepository;
import com.rabelo.tecfood.domain.service.exception.ItemExistenteException;

@Service
public class CadastroEstadoService {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado salvar(Estado estado) {
		Estado estadoId = estadoRepository.findByNome(estado.getNome());
		
		if (estadoId != null) {
			throw new ItemExistenteException("Nome do Estado já existe no BD !");
		}
		return estadoRepository.save(estado);
	}

}
