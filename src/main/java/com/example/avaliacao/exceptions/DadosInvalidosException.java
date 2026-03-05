package com.example.avaliacao.exceptions;

public class DadosInvalidosException extends RuntimeException {
	private static final long serialVersionUID = 1L;
    public DadosInvalidosException(String message) {
        super(message);
    }
}