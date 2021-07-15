package com.rabelo.tecfood;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.rabelo.tecfood.domain.model.Cozinha;
import com.rabelo.tecfood.domain.service.CadastroCozinhaService;
import com.rabelo.tecfood.domain.service.exception.CozinhaNaoEncontradaException;
import com.rabelo.tecfood.domain.service.exception.EntidadeEmUsoException;


@SpringBootTest()
@TestPropertySource("application-test.properties")
class CadastroCozinhasIT {
	@Autowired
	private CadastroCozinhaService cozinhaService;

	@Test
	public void testeCadastroCozinhaComSucesso() {
		//cenário
		Cozinha cozinha = new Cozinha();
		cozinha.setNome("Cozinha do Pará");
		
		//Ação
		cozinha=cozinhaService.salva(cozinha);
		
		//Validação
		assertThat(cozinha).isNotNull();
		assertThat(cozinha.getId()).isNotNull();
	}
	@Test
	public void deveFalhar_QuandoExcluirCozinhaUso() {
		assertThrows(EntidadeEmUsoException.class, () ->{
			cozinhaService.excluir(1L);	
		});
		
	}
	
	@Test
	public void deveFalhar_quandoExcluirCozinhaInexistente() {

		assertThrows(CozinhaNaoEncontradaException.class, () -> {
			cozinhaService.excluir(99L);
		});

	}
	
	@Test
	public void testarCadastroCozinhaSemNome() {

		assertThrows(Exception.class, () -> {

			Cozinha novaCozinha = new Cozinha();
			novaCozinha.setNome(null);

			novaCozinha = cozinhaService.salva(novaCozinha);

		});
	}
	

}
	

