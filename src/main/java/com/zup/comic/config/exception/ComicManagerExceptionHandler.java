package com.zup.comic.config.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import feign.FeignException;


@ControllerAdvice
public class ComicManagerExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource message;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		List<ErroDeValidacao> erros = new ArrayList<>();

		fieldErrors.forEach(fieldError -> {
			String mensagem = message.getMessage(fieldError, LocaleContextHolder.getLocale());
			ErroDeValidacao erro = new ErroDeValidacao(fieldError.getField(), mensagem);
			erros.add(erro);
		});

		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String mensagemUsuario = message.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.getCause().toString();
		List<ErroDeRecurso> erros = Arrays.asList(new ErroDeRecurso(mensagemUsuario, mensagemDesenvolvedor));

		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);

	}

	@ExceptionHandler({ UsuarioExistenteException.class })
	public ResponseEntity<Object> handleUsuarioExistenteException(UsuarioExistenteException ex, WebRequest request) {
		String mensagemUsuario = ex.getMessage();
		String mensagemDesenvolvedor = ex.toString();
		List<ErroDeRecurso> erros = Arrays.asList(new ErroDeRecurso(mensagemUsuario, mensagemDesenvolvedor));

		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

	}
	
	@ExceptionHandler({ ComicNaoDisponivelException.class })
	public ResponseEntity<Object> handleComicNaoDisponivelException(ComicNaoDisponivelException ex, WebRequest request) {
		String mensagemUsuario = ex.getMessage();
		String mensagemDesenvolvedor = ex.toString();
		List<ErroDeRecurso> erros = Arrays.asList(new ErroDeRecurso(mensagemUsuario, mensagemDesenvolvedor));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
		
	}
	
	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
			WebRequest request) {

		String mensagemUsuario = message.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<ErroDeRecurso> erros = Arrays.asList(new ErroDeRecurso(mensagemUsuario, mensagemDesenvolvedor));

		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({ NoSuchElementException.class })
	public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex,
			WebRequest request) {
		
		String mensagemUsuario = message.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<ErroDeRecurso> erros = Arrays.asList(new ErroDeRecurso(mensagemUsuario, mensagemDesenvolvedor));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({ FeignException.class })
	public ResponseEntity<Object> handleFeignException(FeignException ex,
			WebRequest request) {
		
		String mensagemUsuario = message.getMessage("comic.nao-encontrada", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<ErroDeRecurso> erros = Arrays.asList(new ErroDeRecurso(mensagemUsuario, mensagemDesenvolvedor));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	public static class ErroDeRecurso {
		private String mensagemUsuario;
		private String mensagemDesenvolvedor;

		public ErroDeRecurso(String mensagemUsuario, String mensagemDesenvolvedor) {
			this.mensagemUsuario = mensagemUsuario;
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		}

		public String getMensagemUsuario() {
			return mensagemUsuario;
		}

		public String getMensagemDesenvolvedor() {
			return mensagemDesenvolvedor;
		}

	}

	public static class ErroDeValidacao {
		private String campo;
		private String erro;

		public ErroDeValidacao(String campo, String erro) {
			this.campo = campo;
			this.erro = erro;
		}

		public String getCampo() {
			return campo;
		}

		public String getErro() {
			return erro;
		}

	}

}
