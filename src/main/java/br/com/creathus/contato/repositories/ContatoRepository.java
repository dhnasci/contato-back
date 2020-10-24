package br.com.creathus.contato.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.creathus.contato.domain.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Integer> {

}
