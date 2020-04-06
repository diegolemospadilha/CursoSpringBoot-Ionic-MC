package com.lemospadilha.curso.boot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemospadilha.curso.boot.domain.Cidade;
import com.lemospadilha.curso.boot.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;

	public List<Cidade> findAll(Integer estadoId) {
		return repo.findCidades(estadoId);
	}
}
