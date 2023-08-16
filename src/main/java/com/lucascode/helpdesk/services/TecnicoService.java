package com.lucascode.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucascode.helpdesk.domain.Tecnico;
import com.lucascode.helpdesk.repositories.TecnicoRepository;

@Service
public class TecnicoService {

	 @Autowired
	  private TecnicoRepository tecnicoRepository;
	 
	 public Tecnico findById(Integer id) {
		    Optional<Tecnico> object = tecnicoRepository.findById(id);
		    return object.orElse(null);
		  }
	
}
