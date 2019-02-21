package com.lemospadilha.curso.boot.resources;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lemospadilha.curso.boot.domain.Cliente;
import com.lemospadilha.curso.boot.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	ClienteService clienteService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity findById(@PathVariable Integer id) {
		
		Cliente obj = clienteService.buscarPorId(id);		
		return ResponseEntity.ok().body(obj);
	}
	
}
