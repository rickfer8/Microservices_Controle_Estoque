package br.com.controle.estoque.vendafeedback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories
public class ApplicationVendaFeedback {
	
	 public static void main(String[] args) throws InterruptedException {
	        SpringApplication.run(ApplicationVendaFeedback.class, args);
	    }

}
