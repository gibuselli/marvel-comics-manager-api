package com.zup.comic.config.exception;

public class ComicNaoDisponivelException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ComicNaoDisponivelException(String mensagem) {
		super(mensagem);
	}
	
	public ComicNaoDisponivelException(Throwable t) {
		super(t);
	}
}
