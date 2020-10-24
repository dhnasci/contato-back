package br.com.creathus.contato.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.creathus.contato.domain.Contato;
import br.com.creathus.contato.domain.enums.Sexo;

@RestController
@RequestMapping("/contatos")
public class ContatoResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Contato> listar(){	
		List<Contato> lista = new ArrayList();
		Contato cont1 = new Contato(1,"Ronaldo Silva", Sexo.MASCULINO, "3234-3344", "ronaldo@gmail.com");
		Contato cont2 = new Contato(2, "Sonia Pereira", Sexo.FEMININO, "3234-2233", "sonia@cret.com.br");
		
		lista.add(cont1);
		lista.add(cont2);
		return lista;
	}

}
