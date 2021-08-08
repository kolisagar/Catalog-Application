package com.catalog.service.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CatalogServiceBadRequestException extends RuntimeException {

    public CatalogServiceBadRequestException(String message) {
        super(message);
    }
}
