package com.rabelo.tecfood;



import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroCozinhaIT {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private Flyway flayway;
	
	@BeforeEach
	void setup() {
		//verificar os logs no Console
		enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath="/cozinhas";
        
        flayway.migrate();
	}
	
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {
		
	given()
	   .accept(ContentType.JSON)
  .when()
       .get()
  .then()
       .statusCode(HttpStatus.OK.value());	
				
	   
	}
	
	@Test
	void deveRetornarStatus201_QuandoCadastrarCozinha() {
		given()
		    .body("{\"nome\": \"Chinesa\"}")
		    .contentType(ContentType.JSON)
		    .accept(ContentType.JSON)
	.when()
	        .post()
	.then()
	       .statusCode(HttpStatus.CREATED.value());
	}

	
	@Test
	public void deveConter4Cozinhas_QuandoConsultarCozinhas() {
		given()
	   .accept(ContentType.JSON)
  .when()
       .get()
  .then()
 // .body("nome", Matchers.hasSize(4))
  .body("",hasSize(6));
  //Comparar os Dados 
  //.body("nome", hasItems("Indiana", "Cearense", "Japonesa","Americana"));
				
	   
	}
	
	
}