package com.lemospadilha.curso.boot.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.lemospadilha.curso.boot.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencheDataDeValidade(PagamentoComBoleto pagto, Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());

	}
}
