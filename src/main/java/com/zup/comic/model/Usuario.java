package com.zup.comic.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="CPF é obrigatório")
	@Size(min = 11, max = 11, message = "O CPF deve ter 11 caracteres")
	@Column(nullable = false, unique = true, length = 11)
	private String cpf;
	
	@NotBlank(message="Email é obrigatório")
	@Email(message = "Insira um e-mail válido, ex: email@email.com")
	@Column(nullable = false, unique = true, length = 25)
	private String email;

	@NotBlank(message="Nome é obrigatório")
	@Column(nullable = false)
	private String nome;
	
	@NotNull(message = "Data de Nascimento é obrigatório")
	@Column(nullable = false)
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate dataNascimento;
	
	@OneToMany(cascade=CascadeType.PERSIST)
	private List<Comic> comics = new ArrayList<>();
	
	
	
	public Usuario(
			@NotBlank(message = "CPF é obrigatório") @Size(min = 11, max = 11, message = "O CPF deve ter 11 caracteres") String cpf,
			@NotBlank(message = "Email é obrigatório") @Email(message = "Insira um e-mail válido, ex: email@email.com") String email,
			@NotBlank(message = "Nome é obrigatório") String nome,
			@NotNull(message = "Data de Nascimento é obrigatório") LocalDate dataNascimento) {
		this.cpf = cpf;
		this.email = email;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
	}

	public Usuario() {
		super();
	}
	
	public List<Comic> getComics() {
		return comics;
	}

	public void setComics(List<Comic> comics) {
		this.comics = comics;
	}
	
	public void addComics(Comic comic) {
		this.comics.add(comic);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	
	
}
