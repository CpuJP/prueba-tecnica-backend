package com.pruebatecnica.pruebatecnicasc.exceptions;

import org.springframework.http.HttpStatus;

public class MovieConflictException extends ApiException {
    public MovieConflictException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
