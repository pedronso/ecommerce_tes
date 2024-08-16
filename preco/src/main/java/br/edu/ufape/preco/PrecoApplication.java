package br.edu.ufape.preco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class PrecoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrecoApplication.class, args);
	}

}
