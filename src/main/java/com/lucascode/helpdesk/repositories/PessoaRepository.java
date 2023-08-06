package com.lucascode.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucascode.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
