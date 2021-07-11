package com.zup.comic.service;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.zup.comic.config.exception.UsuarioExistenteException;
import com.zup.comic.model.Comic;
import com.zup.comic.model.Usuario;
import com.zup.comic.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;	

	public void salvarUsuario(Usuario usuario) {
		this.verificaUsuarioExistente(usuario);
		repository.save(usuario);
	}
	
	public void verificaUsuarioExistente(@Valid Usuario usuario) {
		this.verificaCpfExistente(usuario);
		this.verificaEmailExistente(usuario);
	}
	
	private void verificaEmailExistente(@Valid Usuario usuario) {
		if (repository.findByEmail(usuario.getEmail()) != null) {
			throw new UsuarioExistenteException("Já existe um usuário cadastrado com esse e-mail");
		}
	}
	
	private void verificaCpfExistente(@Valid Usuario usuario) {
		if (repository.findByCpf(usuario.getCpf()) != null) {
			throw new UsuarioExistenteException("Já existe um usuário cadastrado com esse CPF");
		}
	}
	
	public URI geraUri(@Valid Usuario usuario, UriComponentsBuilder uriBuilder) {
		return uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
	}

	public List<Comic> listarComics(Long id) {
		Usuario usuario = this.buscarUsuario(id);		
		return usuario.getComics();
	}

	public Usuario buscarUsuario(Long id) {
		return repository.findById(id).get();
	}	

	public void salvaComicParaUsuario(Comic comic, Usuario usuario) {
		usuario.addComics(comic);
		repository.save(usuario);
	}
}
