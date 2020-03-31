package com.lemospadilha.curso.boot.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.lemospadilha.curso.boot.services.DBService;
import com.lemospadilha.curso.boot.services.EmailService;
import com.lemospadilha.curso.boot.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategyDB;

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		if (strategyDB.equals("create")) {
			dbService.instantiateTestDatabase();
		}
		return true;
	}

	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
