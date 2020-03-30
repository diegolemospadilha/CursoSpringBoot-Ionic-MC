package com.lemospadilha.curso.boot.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.lemospadilha.curso.boot.domain.Pedido;

@Service
public interface EmailService {
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmatioEmail(Pedido obj);
}
