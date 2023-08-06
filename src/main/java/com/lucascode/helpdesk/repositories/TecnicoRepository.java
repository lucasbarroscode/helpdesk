package com.lucascode.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucascode.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

}
