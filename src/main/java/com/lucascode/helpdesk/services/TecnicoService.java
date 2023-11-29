package com.lucascode.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucascode.helpdesk.domain.Pessoa;
import com.lucascode.helpdesk.domain.Tecnico;
import com.lucascode.helpdesk.domain.dto.TecnicoDTO;
import com.lucascode.helpdesk.repositories.PessoaRepository;
import com.lucascode.helpdesk.repositories.TecnicoRepository;
import com.lucascode.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.lucascode.helpdesk.services.exceptions.ObjectNotFoundException;

import javax.validation.Valid;

@Service
public class TecnicoService {

	 @Autowired
	  private TecnicoRepository tecnicoRepository;
	 
	 @Autowired
	 private PessoaRepository pessoaRepository;
	 
	 public Tecnico findById(Integer id) {
		    Optional<Tecnico> object = tecnicoRepository.findById(id);
		    return object.orElseThrow(() -> new ObjectNotFoundException("Tecnico não encontrado com o id: " + id));
		  }

	public List<Tecnico> findAll() {
		// TODO Auto-generated method stub
		return tecnicoRepository.findAll();
	}
	
	public Tecnico create(TecnicoDTO objDTO) {
		objDTO.setId(null);
		validaPorCPFEEmail(objDTO);
		Tecnico newObj = new Tecnico(objDTO);
		return tecnicoRepository.save(newObj);
	}

	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		objDTO.setId(id);
		Tecnico oldObj = findById(id);
		validaPorCPFEEmail(objDTO);
		oldObj = new Tecnico(objDTO);
		return tecnicoRepository.save(oldObj);
	}

	public void delete(Integer id) {
		 Tecnico obj = findById(id);
		 if(obj.getChamados().size() > 0){
			 throw new DataIntegrityViolationException("Tecnico possui ordens de servico e nao pode ser deletado!!!");
		 }

		 tecnicoRepository.deleteById(id);

	}

	private void validaPorCPFEEmail(TecnicoDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
		}
		
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado no sistema");
		}
		
	}



}
