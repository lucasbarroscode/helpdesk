package com.lucascode.helpdesk.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucascode.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {


	Optional<Pessoa> findByCpf(String cpf);
	Optional<Pessoa> findByEmail(String email);
	
}
