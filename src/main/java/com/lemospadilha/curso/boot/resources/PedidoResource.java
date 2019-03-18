package com.lemospadilha.curso.boot.resources;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lemospadilha.curso.boot.domain.Pedido;
import com.lemospadilha.curso.boot.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired
	PedidoService PedidoService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Pedido> findById(@PathVariable Integer id) {
		
		Pedido obj = PedidoService.findById(id);		
		return ResponseEntity.ok().body(obj);
	}
	
}
