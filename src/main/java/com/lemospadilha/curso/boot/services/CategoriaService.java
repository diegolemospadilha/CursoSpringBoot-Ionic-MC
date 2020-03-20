package com.lemospadilha.curso.boot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lemospadilha.curso.boot.domain.Categoria;
import com.lemospadilha.curso.boot.dto.CategoriaDTO;
import com.lemospadilha.curso.boot.repositories.CategoriaRepository;
import com.lemospadilha.curso.boot.services.exceptions.DataIntegrityException;
import com.lemospadilha.curso.boot.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired 
	private CategoriaRepository categoryRepository;
	
	public Categoria findById(Integer id) {
		Optional<Categoria> obj = categoryRepository.findById(id);
		
		return obj.orElseThrow( () -> new ObjectNotFoundException(
				"Objeto não encontrado Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return categoryRepository.save(categoria);
	}

	public Categoria update(Categoria categoria) {
		findById(categoria.getId());
		return categoryRepository.save(categoria);
	}

	public void delete(Integer id) {
		findById(id);
		try {
			categoryRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
		
	}

	public List<Categoria> findAll() {
		return categoryRepository.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoryRepository.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO catDTO) {
		return new Categoria(catDTO.getId(), catDTO.getNome());
	}
	
	
}
