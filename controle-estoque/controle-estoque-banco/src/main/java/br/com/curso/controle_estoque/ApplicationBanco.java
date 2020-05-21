package br.com.curso.controle_estoque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan
public class ApplicationBanco {
	
	public static void main(String[] args) {
		SpringApplication.run(ApplicationBanco.class, args);
	}

}
