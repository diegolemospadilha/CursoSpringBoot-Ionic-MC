package com.lemospadilha.curso.boot.resources;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lemospadilha.curso.boot.domain.Produto;
import com.lemospadilha.curso.boot.dto.ProdutoDTO;
import com.lemospadilha.curso.boot.resources.utils.URL;
import com.lemospadilha.curso.boot.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	ProdutoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Produto> findById(@PathVariable Integer id) {
		
		Produto obj = service.findById(id);		
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="nome", defaultValue="0") String nome,
			@RequestParam(value="categorias", defaultValue="0") String categorias,
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction
			) {
		
		String nomeFormatado = URL.decodeParam(nome);
		List<Integer> listIds = URL.decodeIntList(categorias);
		Page<Produto> listaProdutos = service.search(nomeFormatado, listIds, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDto = listaProdutos.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
}
