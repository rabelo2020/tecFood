package com.rabelo.tecfood;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.rabelo.tecfood.domain.model.Cozinha;
import com.rabelo.tecfood.domain.repository.CozinhaRepository;
import com.rabelo.tecfood.util.DatabaseCleaner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaIT {

	@LocalServerPort
	private int port;

	@Autowired
	private DatabaseCleaner databaseCleaner;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@BeforeEach
	public void setup() {
		// para verificar os logs no Console
		enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";

		databaseCleaner.clearTables();
		prepararDados();
	}

	@Test
	void deveRetornarStatus201_QuandoCadastrarCozinha() {
		given().body("{\"nome\": \"Chinesa\"}").contentType(ContentType.JSON).accept(ContentType.JSON).when().post()
				.then().statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {

		given().accept(ContentType.JSON).when().get().then().statusCode(HttpStatus.OK.value());

	}

	@Test
	void deveRetornarRespostaStatusCorretos_QuandoConsultarCozinhaExistente() {
		given().pathParam("cozinhaId", 2).accept(ContentType.JSON).when().get("/{cozinhaId}").then()
				.statusCode(HttpStatus.OK.value()).body("nome", equalTo("Tailandesa"));
	}

	@Test
	void deveRetornarRespostaStatus404_QuandoConsultarCozinhaEnexistente() {
		given().pathParam("cozinhaId", 20).accept(ContentType.JSON).when().get("/{cozinhaId}").then()
				.statusCode(HttpStatus.NOT_FOUND.value());
	}

	@Test
	public void deveConter2Cozinhas_QuandoConsultarCozinhas() {
		given().accept(ContentType.JSON).when().get().then()
				// .body("nome", Matchers.hasSize(4))
				.body("", hasSize(2));
		// Comparar os Dados
		// .body("nome", hasItems("Indiana", "Cearense", "Japonesa","Americana"));

	}

	@Test
	void deveRetornarRespostaStatus204_QuandoExcluirCozinhaExistente() {
		given().pathParam("cozinhaId", 1).accept(ContentType.JSON).when().delete("/{cozinhaId}").then()
				.statusCode(HttpStatus.NO_CONTENT.value());
	}

	private void prepararDados() {
		Cozinha cozinha = new Cozinha();
		cozinha.setNome("Americana");
		cozinhaRepository.save(cozinha);

		Cozinha cozinha02 = new Cozinha();
		cozinha02.setNome("Tailandesa");
		cozinhaRepository.save(cozinha02);
	}

}
