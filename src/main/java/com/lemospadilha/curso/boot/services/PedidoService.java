package com.lemospadilha.curso.boot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemospadilha.curso.boot.domain.Pedido;
import com.lemospadilha.curso.boot.repositories.PedidoRepository;
import com.lemospadilha.curso.boot.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired 
	private PedidoRepository PedidoRepository;
	
	public Pedido findById(Integer id) {
		Optional<Pedido> obj = PedidoRepository.findById(id);
		
		return obj.orElseThrow( () -> new ObjectNotFoundException(
				"Objeto não encontrado Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	
}
