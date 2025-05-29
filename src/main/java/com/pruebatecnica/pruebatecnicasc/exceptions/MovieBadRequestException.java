package com.pruebatecnica.pruebatecnicasc.exceptions;

import org.springframework.http.HttpStatus;

public class MovieBadRequestException extends ApiException {
    public MovieBadRequestException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
