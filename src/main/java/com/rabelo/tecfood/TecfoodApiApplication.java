package com.rabelo.tecfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.rabelo.tecfood.domain.infrastructure.repository.teste.CustomJpaRepositoryImpl;
import com.rabelo.tecfood.domain.repository.teste.CustomJpaRepository;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class TecfoodApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TecfoodApiApplication.class, args);
	}

}
