package br.com.creathus.contato.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.creathus.contato.domain.Contato;
import br.com.creathus.contato.domain.enums.Sexo;
import br.com.creathus.contato.repositories.ContatoRepository;

@Service
public class DBService {
	
	@Autowired
	private ContatoRepository contatoRepo; 
	
	public void instantiateTestDatabase() {
		Contato cont1 = new Contato(null, "Ronaldo Mangi", Sexo.MASCULINO, "3233-3344", "ronaldo@mail.com" );
		Contato cont2 = new Contato(null, "Silvia Maiara", Sexo.FEMININO, "3233-1344", "silvia@mail.com");
		Contato cont3 = new Contato(null, "Sandro Rosas", Sexo.MASCULINO, "2334-1212", "sandror@mail.com");
		
		contatoRepo.saveAll(Arrays.asList(cont1, cont2, cont3));
	}

}
