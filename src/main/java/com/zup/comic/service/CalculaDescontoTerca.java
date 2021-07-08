package com.zup.comic.service;

import static java.math.BigDecimal.ROUND_HALF_EVEN;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;

import com.zup.comic.model.Comic;

public class CalculaDescontoTerca implements CalculadoraDeDesconto {

	@Override
	public BigDecimal calculaDesconto(Comic comic) {
			BigDecimal desconto = comic.precoSemDesconto().multiply(BigDecimal.valueOf(0.10));
			BigDecimal precoComDesconto = comic.precoSemDesconto().subtract(desconto);
			return precoComDesconto.setScale(2, RoundingMode.HALF_EVEN);
	}

	@Override
	public void isDescontoAtivo(Comic comic) {
		DayOfWeek dia = LocalDate.now().getDayOfWeek();
		
		if (dia.equals(dia.TUESDAY)) {
			comic.setDescontoAtivo(true);			
		} else {
			comic.setDescontoAtivo(false);
		}
		
		
	}

}
