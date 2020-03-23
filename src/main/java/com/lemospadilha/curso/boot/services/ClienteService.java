package com.lemospadilha.curso.boot.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lemospadilha.curso.boot.domain.Cliente;
import com.lemospadilha.curso.boot.dto.ClienteDTO;
import com.lemospadilha.curso.boot.repositories.ClienteRepository;
import com.lemospadilha.curso.boot.services.exceptions.DataIntegrityException;
import com.lemospadilha.curso.boot.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired 
	private ClienteRepository repo;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow( () -> new ObjectNotFoundException(
				"Cliente não encontrado Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}	

	public void delete(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma cliente que possui produtos");
		}
		
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public ClienteDTO fromDTO(Cliente model) {
		return new ClienteDTO(model);
	}

	public Cliente fromDTO(@Valid ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(),  null, null);
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
	}
	
	
}
