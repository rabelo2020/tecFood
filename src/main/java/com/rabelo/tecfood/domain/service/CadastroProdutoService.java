package com.rabelo.tecfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rabelo.tecfood.domain.model.Produto;
import com.rabelo.tecfood.domain.model.Restaurante;
import com.rabelo.tecfood.domain.repository.ProdutoRepository;
import com.rabelo.tecfood.domain.service.exception.EntidadeEmUsoException;
import com.rabelo.tecfood.domain.service.exception.EntidadeJaCadastradaException;
import com.rabelo.tecfood.domain.service.exception.EntidadeNaoEncontradaException;
import com.rabelo.tecfood.domain.service.exception.ProdutoNaoEncontradoException;

@Service
public class CadastroProdutoService {

	private static final String PRODUTO_JA_CADASTRADO = "Produto já Cadastrado";
	private static final String PRODUTO_NAO_CADASTRADO = "Produto de Código %s, não Cadastrado";
	private static final String PRODUTO_ESTA_USO = "Produto de Código %d, está sendo usado";

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;

	@Transactional
	public Produto salvar(Produto produto) {
		//Long restauranteId = produto.getRestaurante().getId();
		//existeProduto(produto.getNome());
		
		//Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
		//produto.setRestaurante(restaurante);		
		
		return produtoRepository.save(produto);
	}

	public void existeProduto(String nome) {
		boolean produto = produtoRepository.existsByNome(nome.trim());
		if (produto) {
			throw new EntidadeJaCadastradaException(String.format(PRODUTO_JA_CADASTRADO, nome.toUpperCase()));
		}

	}
	
@Transactional
	public void excluir(Long id) {
		Produto produto = buscarOuFalhar(id);
		
		try {
			produtoRepository.delete(produto);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(PRODUTO_ESTA_USO, id));
		}
	}


	public Produto buscarOuFalhar(Long id) {

		return produtoRepository.findById(id)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(String.format(PRODUTO_NAO_CADASTRADO, id)));
	}
		
	

}
