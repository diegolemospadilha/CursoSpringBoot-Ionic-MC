package com.lemospadilha.curso.boot.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lemospadilha.curso.boot.domain.Categoria;
import com.lemospadilha.curso.boot.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	CategoriaService categoriaService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity findById(@PathVariable Integer id) {
		
		Categoria obj = categoriaService.buscarPorId(id);		
		return ResponseEntity.ok().body(obj);
	}
	
}
