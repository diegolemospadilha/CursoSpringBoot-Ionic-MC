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
import org.springframework.transaction.annotation.Transactional;

import com.lemospadilha.curso.boot.domain.Cidade;
import com.lemospadilha.curso.boot.domain.Cliente;
import com.lemospadilha.curso.boot.domain.Endereco;
import com.lemospadilha.curso.boot.domain.enums.TipoCliente;
import com.lemospadilha.curso.boot.dto.ClienteDTO;
import com.lemospadilha.curso.boot.dto.ClienteNewDTO;
import com.lemospadilha.curso.boot.repositories.ClienteRepository;
import com.lemospadilha.curso.boot.repositories.EnderecoRepository;
import com.lemospadilha.curso.boot.services.exceptions.DataIntegrityException;
import com.lemospadilha.curso.boot.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired 
	private ClienteRepository repo;
	
	@Autowired 
	private EnderecoRepository enderecoRepo;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow( () -> new ObjectNotFoundException(
				"Cliente não encontrado Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		enderecoRepo.saveAll(obj.getEnderecos());
		return repo.save(obj);
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
	
	public Cliente fromDTO(@Valid ClienteNewDTO dto) {
		Cliente cliente = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpfOuCnpj(), TipoCliente.toEnum(dto.getTipo()));
		Cidade cid = new Cidade();
		cid.setId(dto.getCidadeId());
		Endereco end = new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(), dto.getBairro(), dto.getCep(), cliente, cid);
		cliente.getEnderecos().add(end);
		cliente.getTelefones().add(dto.getTelefone1());
		if(dto.getTelefone2() != null) {
			cliente.getTelefones().add(dto.getTelefone2());
		}
		if(dto.getTelefone3() != null) {
			cliente.getTelefones().add(dto.getTelefone3());
		}
		
		return cliente;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());	
	}

	
}
