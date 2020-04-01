package com.lemospadilha.curso.boot.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class CredenciasDTO {
	
	@Email(message = "E-mail inválido")
	private String email;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String senha;
	
	public CredenciasDTO() {
	}
	
	public CredenciasDTO( String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
