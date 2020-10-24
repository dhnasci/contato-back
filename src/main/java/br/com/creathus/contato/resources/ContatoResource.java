package br.com.creathus.contato.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.creathus.contato.domain.Contato;
import br.com.creathus.contato.services.ContatoService;

@RestController
@RequestMapping("/contatos")
public class ContatoResource {
	
	@Autowired
	private ContatoService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id){
		Contato obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> list(){
		List<Contato> lista = service.findAll();
		return ResponseEntity.ok().body(lista);
	}
	
}
