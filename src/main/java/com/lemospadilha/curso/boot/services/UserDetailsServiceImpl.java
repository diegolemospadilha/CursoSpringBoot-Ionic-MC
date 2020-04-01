package com.lemospadilha.curso.boot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lemospadilha.curso.boot.domain.Cliente;
import com.lemospadilha.curso.boot.repositories.ClienteRepository;
import com.lemospadilha.curso.boot.security.UserSS;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente cliente = clienteRepository.findByEmail(email);
		
		if(cliente == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return new UserSS(cliente.getId(),cliente.getEmail(), cliente.getSenha(), cliente.getPerfis());
		
	}

}
