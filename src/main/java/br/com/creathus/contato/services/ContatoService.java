package br.com.creathus.contato.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.creathus.contato.domain.Contato;
import br.com.creathus.contato.repositories.ContatoRepository;

@Service
public class ContatoService {
	
	@Autowired
	private ContatoRepository repo;
	
	public Contato find(Integer id) {
		Optional<Contato> obj = repo.findById(id);
		return obj.orElse(null);
	}
	
	public List<Contato> findAll(){
		return repo.findAll();
	}

}
