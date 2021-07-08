package com.zup.comic.service;

import java.math.BigDecimal;

import com.zup.comic.model.Comic;

public interface CalculadoraDeDesconto {
	
	public void isDescontoAtivo(Comic comic);
	
	public BigDecimal calculaDesconto(Comic comic);

}
