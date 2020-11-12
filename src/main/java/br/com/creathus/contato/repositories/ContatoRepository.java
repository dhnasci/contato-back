package br.com.creathus.contato.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.creathus.contato.domain.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Integer> {
	
	@Transactional(readOnly=true)
	public List<Contato> findAllByOrderByNome();

}
