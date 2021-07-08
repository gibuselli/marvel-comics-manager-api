package com.zup.comic.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;


@Entity
public class Comic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titulo;

	@Size(max = 1000)
	private String descricao;

	private BigDecimal preco;

	@ElementCollection(targetClass = String.class)
	private List<String> autores = new ArrayList<>();	

	private String isbn;
	
	private DiaDoDesconto diaDoDesconto;
	
	private boolean descontoAtivo;

	public Comic(String titulo, String descricao, BigDecimal preco, String isbn) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.preco = preco;
		this.isbn = isbn;
	}
	
	public Comic() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		this.getDiaDoDesconto().getCalculadora().isDescontoAtivo(this);
		if (this.descontoAtivo) {
			preco = this.getDiaDoDesconto().getCalculadora().calculaDesconto(this);
			return preco;
		}
		return preco;
	}
	
	public BigDecimal precoSemDesconto() {		
		return preco;
	}


	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public List<String> getAutores() {
		return autores;
	}

	public void setAutores(List<String> autores) {
		this.autores = autores;
	}
	
	public void addAutores(String autor) {
		this.autores.add(autor);
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public DiaDoDesconto getDiaDoDesconto() {
		return diaDoDesconto;
	}

	public void setDiaDoDesconto(DiaDoDesconto diaDoDesconto) {
		this.diaDoDesconto = diaDoDesconto;
	}

	public boolean isDescontoAtivo() {
		return descontoAtivo;
	}

	public void setDescontoAtivo(boolean descontoAtivo) {
		this.descontoAtivo = descontoAtivo;
	}
	
	

}
