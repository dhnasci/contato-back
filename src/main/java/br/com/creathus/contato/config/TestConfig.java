package br.com.creathus.contato.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.creathus.contato.services.DBService;

@Configuration
@Profile("dev")
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDatabase()   {
		
		dbService.instantiateTestDatabase();
		
		return true;
	}

}
