package com.example.avaliacao.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.avaliacao.exceptions.DadosInvalidosException;
import com.example.avaliacao.exceptions.EmailJaExisteException;
import com.example.avaliacao.exceptions.NaoEncontradoException;

@ControllerAdvice
public class ExceptionHandlers {
	
    // 1. Trata o erro 404 (Registro não encontrado)
    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<String> recursoNaoEncontrado(NaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
	
    // 2. Trata o erro 400 (Dados Inválidos - REQUISITO DO PROFESSOR)
    @ExceptionHandler(DadosInvalidosException.class)
    public ResponseEntity<String> dadosInvalidos(DadosInvalidosException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // 3. Trata o erro 409 (E-mail repetido)
    @ExceptionHandler(EmailJaExisteException.class)
    public ResponseEntity<String> emailJaExiste(EmailJaExisteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
	
    // 4. Trata qualquer outro erro inesperado do sistema (Erro 500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> excecaoGeral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro interno no servidor: " + ex.getMessage());
    }
}