package com.lemospadilha.curso.boot.resources;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lemospadilha.curso.boot.domain.Cidade;
import com.lemospadilha.curso.boot.domain.Estado;
import com.lemospadilha.curso.boot.dto.CidadeDTO;
import com.lemospadilha.curso.boot.dto.EstadoDTO;
import com.lemospadilha.curso.boot.services.CidadeService;
import com.lemospadilha.curso.boot.services.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoResource {
	
	@Autowired
	EstadoService service;
	
	@Autowired
	CidadeService cidadeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll() {
		
		List<Estado> listEstados = service.findAll();
		List<EstadoDTO> listDto = listEstados.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value = "/{estadoId}/cidades", method=RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findCidadesByEstado(@PathVariable Integer estadoId) {
		
		List<Cidade> listCidades = cidadeService.findAll(estadoId);
		List<CidadeDTO> listDto = listCidades.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
}
