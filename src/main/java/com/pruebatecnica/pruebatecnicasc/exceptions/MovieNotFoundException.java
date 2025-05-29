package com.pruebatecnica.pruebatecnicasc.exceptions;

import org.springframework.http.HttpStatus;

public class MovieNotFoundException extends ApiException {
    public MovieNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
