package br.com.creathus.contato.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.creathus.contato.domain.Usuario;
import br.com.creathus.contato.dto.UsuarioDTO;
import br.com.creathus.contato.repositories.UsuarioRepository;
import br.com.creathus.contato.services.exceptions.DataIntegrityException;
import br.com.creathus.contato.services.exceptions.ObjectNotFoundException;
import br.com.creathus.contato.services.exceptions.RejectException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public Usuario find(Integer id) {
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}
	
	public List<Usuario> findAll(){
		return repo.findAll();
	}
	
	public Usuario insert(Usuario obj) {
		obj.setId(null);
		Usuario addObj = new Usuario();
		try {
			addObj = repo.save(obj);
		} catch (RejectException e) {
			// TODO: handle exception
			throw new RejectException("Adição de Usuario rejeitada! ", e);
		}
		return addObj;
	}
	
	public Usuario update(Usuario obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		
		find(id);
		
		try {
			repo.deleteById(id);
			
		} catch (DataIntegrityException e) {
			// TODO: handle exception
			throw new DataIntegrityException("Não é possível excluir o Usuario por integridade referencial", e);
		}
		
	}
	
	Usuario fromDTO(UsuarioDTO usudto) {
		return new Usuario(usudto.getId(), usudto.getName(), usudto.getEmail(), pe.encode(usudto.getPassword()) , usudto.getPerfil());
	}

}
