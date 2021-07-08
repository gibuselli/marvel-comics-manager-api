package com.zup.comic.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.zup.comic.config.exception.ComicNaoDisponivelException;
import com.zup.comic.model.Comic;
import com.zup.comic.model.Usuario;
import com.zup.comic.service.ComicService;
import com.zup.comic.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired ComicService comicService;
	
	@GetMapping
	public ResponseEntity<?> listarUsuarios(String nome) {
		List<Usuario> listaUsuarios = usuarioService.listarUsuarios(nome);
		return !listaUsuarios.isEmpty() ? ResponseEntity.ok(listaUsuarios) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/myComics/{id}")
	public ResponseEntity<?> listarComicsDoUsuario(@PathVariable Long id) {
		List<Comic> listaComics = usuarioService.listarComics(id);
		return !listaComics.isEmpty() ? ResponseEntity.ok(listaComics) : ResponseEntity.noContent().build();
	}

	@PostMapping
	public ResponseEntity<Usuario> cadastrarUsuario(@Valid @RequestBody Usuario usuario, UriComponentsBuilder uriBuilder) {

		usuarioService.salvarUsuario(usuario);				
		
		return ResponseEntity.created(usuarioService.geraUri(usuario, uriBuilder)).body(usuario);
	}
	
	@PostMapping("/myComics/{usuarioId}/{comicId}")
	public ResponseEntity<?> cadastrarComicParaUsuario(@PathVariable Long usuarioId, @PathVariable Long comicId, UriComponentsBuilder uriBuilder) throws ComicNaoDisponivelException {

		Comic comic = comicService.salvarComic(comicId);		
		Usuario usuario = usuarioService.buscarUsuario(usuarioId);
		
		usuarioService.salvaComicParaUsuario(comic, usuario);
		
		return ResponseEntity.created(comicService.geraUri(usuario, comic, uriBuilder)).body(usuario);
	}
	
}





