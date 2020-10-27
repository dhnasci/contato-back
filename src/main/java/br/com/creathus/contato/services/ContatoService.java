package br.com.creathus.contato.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.creathus.contato.domain.Contato;
import br.com.creathus.contato.repositories.ContatoRepository;
import br.com.creathus.contato.services.exceptions.DataIntegrityException;
import br.com.creathus.contato.services.exceptions.ObjectNotFoundException;

@Service
public class ContatoService {
	
	@Autowired
	private ContatoRepository repo;
	
	public Contato find(Integer id) {
		Optional<Contato> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}
	
	public List<Contato> findAll(){
		return repo.findAll();
	}
	
	public Contato insert(Contato obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Contato update(Contato obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		
		find(id);
		
		try {
			repo.deleteById(id);
			
		} catch (DataIntegrityException e) {
			// TODO: handle exception
			throw new DataIntegrityException("Não é possível excluir o Contato por integridade referencial");
		}
		
	}

}
