package com.zup.comic.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zup.comic.client.ComicDTO;
import com.zup.comic.client.MarvelEndpoint;

@RestController
@RequestMapping("/comics")
public class ComicController {

	@Autowired
	private MarvelEndpoint endpoint;
	

	
	@GetMapping
	public ResponseEntity<?> listarComicsDisponiveis() {
		ComicDTO comics = endpoint.listarComics();
		return comics != null ? ResponseEntity.ok(comics) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<ComicDTO> listarComicsDisponiveisPorId(@PathVariable Long id) {
		ComicDTO comic = endpoint.buscaComicPorId(id);
        return comic != null ? ResponseEntity.ok(comic) : ResponseEntity.notFound().build(); 
    }	


}
