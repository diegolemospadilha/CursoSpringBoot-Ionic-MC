package com.lemospadilha.curso.boot.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.lemospadilha.curso.boot.domain.Cliente;
import com.lemospadilha.curso.boot.domain.Pedido;

@Service
public interface EmailService {
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendHtmlEmail(MimeMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Pedido obj);

	void sendNewPassword(Cliente cliente, String newPass);

	void sendNewPasswordHtmlEmail(Cliente cliente, String newPass);
}
