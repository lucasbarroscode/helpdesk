package com.lucascode.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucascode.helpdesk.domain.Tecnico;
import com.lucascode.helpdesk.domain.dto.TecnicoDTO;
import com.lucascode.helpdesk.repositories.TecnicoRepository;
import com.lucascode.helpdesk.services.exceptions.ObjectNotFoundExcption;

@Service
public class TecnicoService {

	 @Autowired
	  private TecnicoRepository tecnicoRepository;
	 
	 public Tecnico findById(Integer id) {
		    Optional<Tecnico> object = tecnicoRepository.findById(id);
		    return object.orElseThrow(() -> new ObjectNotFoundExcption("Tecnico não encontrado com o id: " + id));
		  }

	public List<Tecnico> findAll() {
		// TODO Auto-generated method stub
		return tecnicoRepository.findAll();
	}
	
	public Tecnico create(TecnicoDTO objDTO) {
		objDTO.setId(null);
		Tecnico newObj = new Tecnico(objDTO);
		return tecnicoRepository.save(newObj);
	}
	
}
