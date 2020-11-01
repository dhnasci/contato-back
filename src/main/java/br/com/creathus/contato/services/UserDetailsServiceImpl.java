package br.com.creathus.contato.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.creathus.contato.domain.Usuario;
import br.com.creathus.contato.repositories.UsuarioRepository;
import br.com.creathus.contato.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Usuario usu = repo.findByEmail(email);
		
		if (usu == null) {
			throw new UsernameNotFoundException("Usuário não encontrado: " + email);
		}
		
		return new UserSS(usu.getId(), usu.getEmail(), usu.getPassword(), usu.getPerfis());
	}

}
