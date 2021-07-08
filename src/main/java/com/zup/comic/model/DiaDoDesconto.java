package com.zup.comic.model;

import com.zup.comic.service.CalculaDescontoQuarta;
import com.zup.comic.service.CalculaDescontoQuinta;
import com.zup.comic.service.CalculaDescontoSegunda;
import com.zup.comic.service.CalculaDescontoSexta;
import com.zup.comic.service.CalculaDescontoTerca;
import com.zup.comic.service.CalculadoraDeDesconto;

public enum DiaDoDesconto {

	SEGUNDA(new CalculaDescontoSegunda()),
	TERCA(new CalculaDescontoTerca()),
	QUARTA(new CalculaDescontoQuarta()),
	QUINTA(new CalculaDescontoQuinta()),
	SEXTA(new CalculaDescontoSexta());
	
	private CalculadoraDeDesconto calculadora;
	
	DiaDoDesconto(CalculadoraDeDesconto calculadora) {
		this.calculadora = calculadora;
	}

	public CalculadoraDeDesconto getCalculadora() {
		return calculadora;
	}

	public void setCalculadora(CalculadoraDeDesconto calculadora) {
		this.calculadora = calculadora;
	}
	
	
}
