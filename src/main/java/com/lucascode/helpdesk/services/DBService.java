package com.lucascode.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucascode.helpdesk.domain.Chamado;
import com.lucascode.helpdesk.domain.Cliente;
import com.lucascode.helpdesk.domain.Tecnico;
import com.lucascode.helpdesk.domain.enums.Perfil;
import com.lucascode.helpdesk.domain.enums.Prioridade;
import com.lucascode.helpdesk.domain.enums.Status;
import com.lucascode.helpdesk.repositories.ChamadoRepository;
import com.lucascode.helpdesk.repositories.ClienteRepository;
import com.lucascode.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;

	public void instanciaDB() {
		
		Tecnico tec1 = new Tecnico(null, "Valdir Souza", "481.056.770-24", "valdir@mail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
		
		
		Cliente cli1 = new Cliente(null, "Linux Torvalds", "810.539.140-67", "torvalds@mail.com", "123");
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", tec1, cli1);
		
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
		
	}
	
}
