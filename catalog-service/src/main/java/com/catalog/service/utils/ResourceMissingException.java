package com.catalog.service.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceMissingException extends RuntimeException {

    public ResourceMissingException(String message) {
        super(message);
    }
}
