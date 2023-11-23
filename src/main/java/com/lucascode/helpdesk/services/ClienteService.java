package com.lucascode.helpdesk.services;

import com.lucascode.helpdesk.domain.Pessoa;
import com.lucascode.helpdesk.domain.Cliente;
import com.lucascode.helpdesk.domain.dto.ClienteDTO;
import com.lucascode.helpdesk.repositories.PessoaRepository;
import com.lucascode.helpdesk.repositories.ClienteRepository;
import com.lucascode.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.lucascode.helpdesk.services.exceptions.ObjectNotFoundExcption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

	 @Autowired
	  private ClienteRepository tecnicoRepository;
	 
	 @Autowired
	 private PessoaRepository pessoaRepository;
	 
	 public Cliente findById(Integer id) {
		    Optional<Cliente> object = tecnicoRepository.findById(id);
		    return object.orElseThrow(() -> new ObjectNotFoundExcption("Cliente não encontrado com o id: " + id));
		  }

	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return tecnicoRepository.findAll();
	}
	
	public Cliente create(ClienteDTO objDTO) {
		objDTO.setId(null);
		validaPorCPFEEmail(objDTO);
		Cliente newObj = new Cliente(objDTO);
		return tecnicoRepository.save(newObj);
	}

	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id);
		Cliente oldObj = findById(id);
		validaPorCPFEEmail(objDTO);
		oldObj = new Cliente(objDTO);
		return tecnicoRepository.save(oldObj);
	}

	public void delete(Integer id) {
		 Cliente obj = findById(id);
		 if(obj.getChamados().size() > 0){
			 throw new DataIntegrityViolationException("Cliente possui ordens de servico e nao pode ser deletado!!!");
		 }

		 tecnicoRepository.deleteById(id);

	}

	private void validaPorCPFEEmail(ClienteDTO objDTO) {
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
