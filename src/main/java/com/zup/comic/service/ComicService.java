package com.zup.comic.service;

import java.math.BigDecimal;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.zup.comic.client.ComicDTO;
import com.zup.comic.client.MarvelEndpoint;
import com.zup.comic.config.exception.ComicNaoDisponivelException;
import com.zup.comic.model.Comic;
import com.zup.comic.model.DiaDoDesconto;
import com.zup.comic.model.Usuario;
import com.zup.comic.repository.ComicRepository;

@Service
public class ComicService {

	@Autowired
	private MarvelEndpoint endpoint;

	@Autowired
	private ComicRepository comicRepository;

	public Comic salvarComic(Long comicId) throws ComicNaoDisponivelException {
		ComicDTO comicDto = endpoint.buscaComicPorId(comicId);
		Comic comic = converteComicDto(comicDto);

		defineDiaDoDesconto(comic);
		comicRepository.save(comic);

		return comic;
	}

	private Comic converteComicDto(ComicDTO comicDto) throws ComicNaoDisponivelException {
		Comic comic = new Comic(comicDto.getData().getResults().get(0).getTitle(),
				comicDto.getData().getResults().get(0).getDescription(),
				comicDto.getData().getResults().get(0).getPrices().get(0).getPrice(),
				comicDto.getData().getResults().get(0).getIsbn());

		if (comic.precoSemDesconto() == null || comic.precoSemDesconto().equals(BigDecimal.ZERO)
				|| comic.getIsbn().isBlank()) {
			throw new ComicNaoDisponivelException("A comic solicitada ainda não tem preço e/ou ISBN disponíveis");
		}

		for (int i = 0; i < comicDto.getData().getResults().get(0).getCreators().getAvailable(); i++) {
			comic.addAutores(comicDto.getData().getResults().get(0).getCreators().getItems().get(i).getName());
		}

		return comic;
	}

	private void defineDiaDoDesconto(Comic comic) {
		String isbn = comic.getIsbn();
		String finalIsbn = String.valueOf(isbn.charAt(isbn.length() - 1));

		switch (finalIsbn) {
		case "0":
		case "1":
			comic.setDiaDoDesconto(DiaDoDesconto.SEGUNDA);
			break;
		case "2":
		case "3":
			comic.setDiaDoDesconto(DiaDoDesconto.TERCA);
			break;
		case "4":
		case "5":
			comic.setDiaDoDesconto(DiaDoDesconto.QUARTA);
			break;
		case "6":
		case "7":
			comic.setDiaDoDesconto(DiaDoDesconto.QUINTA);
			break;
		case "8":
		case "9":
			comic.setDiaDoDesconto(DiaDoDesconto.SEXTA);
			break;
		}
	}

	public URI geraUri(Usuario usuario, Comic comic, UriComponentsBuilder uriBuilder) {
		return uriBuilder.path("/usuario/myComics/{usuarioId}/{comicId}").buildAndExpand(usuario.getId(), 			comic.getId()).toUri();
	}

}
