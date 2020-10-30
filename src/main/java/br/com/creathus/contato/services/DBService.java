package br.com.creathus.contato.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.creathus.contato.domain.Contato;
import br.com.creathus.contato.domain.Usuario;
import br.com.creathus.contato.domain.enums.Perfil;
import br.com.creathus.contato.domain.enums.Sexo;
import br.com.creathus.contato.repositories.ContatoRepository;
import br.com.creathus.contato.repositories.UsuarioRepository;

@Service
public class DBService {
	
	@Autowired
	private ContatoRepository contatoRepo; 
	
	@Autowired
	private UsuarioRepository usuarioRepo; 
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	public void instantiateTestDatabase() {
		
		Contato cont1 = new Contato(null, "Ronaldo Mangi", Sexo.MASCULINO, "3233-3344", "ronaldo@mail.com" );
		Contato cont2 = new Contato(null, "Silvia Maiara", Sexo.FEMININO, "3233-1344", "silvia@mail.com");
		Contato cont3 = new Contato(null, "Sandro Rosas", Sexo.MASCULINO, "2334-1212", "sandror@mail.com");
		
		contatoRepo.saveAll(Arrays.asList(cont1, cont2, cont3));
		
		Usuario usu1 = new Usuario(null, "Administrador", "dhnasci@gmail.com", bcrypt.encode("admin123") , Perfil.ADM);
		Usuario usu2 = new Usuario(null, "Financeiro", "fin@email.com", bcrypt.encode("fin123"), Perfil.FIN);
		Usuario usu3 = new Usuario(null, "Colaborador", "colab@email.com", bcrypt.encode("colab"), null);
		
		usuarioRepo.saveAll(Arrays.asList(usu1, usu2, usu3));
		
		
	}

}
