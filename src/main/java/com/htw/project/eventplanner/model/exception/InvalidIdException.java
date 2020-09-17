package com.htw.project.eventplanner.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InvalidIdException extends Exception {

    public InvalidIdException(String message) {
        super(message);
    }

}
