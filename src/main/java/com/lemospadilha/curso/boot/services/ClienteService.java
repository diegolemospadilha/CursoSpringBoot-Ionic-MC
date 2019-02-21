package com.lemospadilha.curso.boot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemospadilha.curso.boot.domain.Cliente;
import com.lemospadilha.curso.boot.repositories.ClienteRepository;
import com.lemospadilha.curso.boot.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired 
	private ClienteRepository clienteRepository;
	
	public Cliente buscarPorId(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		
		return obj.orElseThrow( () -> new ObjectNotFoundException(
				"Objeto não encontrado Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	
}
