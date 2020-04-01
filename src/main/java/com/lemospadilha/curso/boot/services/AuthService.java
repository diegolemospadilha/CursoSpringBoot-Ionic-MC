package com.lemospadilha.curso.boot.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lemospadilha.curso.boot.domain.Cliente;
import com.lemospadilha.curso.boot.repositories.ClienteRepository;
import com.lemospadilha.curso.boot.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder be;
	
	@Autowired
	private EmailService emailService;
	
	private Random random =  new Random();
	
	public Cliente sendNewPassword(String email) {
		Cliente cliente = clienteRepository.findByEmail(email);
		if(cliente == null) {
			throw new ObjectNotFoundException("E-mail não encontrado");
		}
		
		String newPass = newPassword();
		cliente.setSenha(be.encode(newPass));
		clienteRepository.save(cliente);
		emailService.sendNewPasswordHtmlEmail(cliente, newPass );
		return cliente;
	}


	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < vet.length; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = random.nextInt(3);
		switch (opt) {
		case 0: // gera um digito
			return (char) (random.nextInt(10) + 48);
		case 1:  // gera uma letra maiscula
			return (char) (random.nextInt(26) + 65);

		default:  // gera uma letra minuscula
			return (char) (random.nextInt(26) + 97);
		}
		
	}
}
