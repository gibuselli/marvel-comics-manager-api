package com.zup.comic.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.zup.comic.model.Comic;

@FeignClient(name = "comic", url = "http://gateway.marvel.com/v1/public/comics?ts=1625528783385&apikey=f9ee3d5907a9fea18826aa8f9defac36&hash=277110bb3d3c5474957e73e430a17ae9")
public interface MarvelEndpoint {

	
	@GetMapping()
	ComicDTO listarComics();
	
	@GetMapping("{id}")
    ComicDTO buscaComicPorId(@PathVariable("id") Long id);


}
