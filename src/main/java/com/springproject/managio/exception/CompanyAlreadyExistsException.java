package com.springproject.managio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CompanyAlreadyExistsException extends ResponseStatusException {

    public CompanyAlreadyExistsException(String reason) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, reason);
    }
}