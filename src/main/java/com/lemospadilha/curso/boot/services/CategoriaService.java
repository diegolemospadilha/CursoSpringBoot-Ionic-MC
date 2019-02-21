package com.lemospadilha.curso.boot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemospadilha.curso.boot.domain.Categoria;
import com.lemospadilha.curso.boot.repositories.CategoriaRepository;
import com.lemospadilha.curso.boot.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired 
	private CategoriaRepository categoryRepository;
	
	public Categoria buscarPorId(Integer id) {
		Optional<Categoria> obj = categoryRepository.findById(id);
		
		return obj.orElseThrow( () -> new ObjectNotFoundException(
				"Objeto não encontrado Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	
}
