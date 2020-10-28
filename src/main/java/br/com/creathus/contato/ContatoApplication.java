package br.com.creathus.contato;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.creathus.contato.domain.Contato;
import br.com.creathus.contato.domain.enums.Sexo;
import br.com.creathus.contato.repositories.ContatoRepository;

@SpringBootApplication
public class ContatoApplication implements CommandLineRunner {
	
	
	public static void main(String[] args) {
		SpringApplication.run(ContatoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		
	}

}
