package com.prueba.tecnica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ErrorNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErrorNotFoundException(String mensajeError) {
		super(mensajeError);
	}
}
