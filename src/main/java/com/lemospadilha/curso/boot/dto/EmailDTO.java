package com.lemospadilha.curso.boot.dto;

import javax.validation.constraints.Email;

public class EmailDTO {
	
	@Email(message = "E-mail inválido")
	private String email;
	
	public EmailDTO() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
