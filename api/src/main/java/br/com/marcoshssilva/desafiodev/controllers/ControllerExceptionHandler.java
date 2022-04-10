package br.com.marcoshssilva.desafiodev.controllers;

import br.com.marcoshssilva.desafiodev.models.ErrorModel;
import br.com.marcoshssilva.desafiodev.services.exceptions.NoIdEntityException;
import br.com.marcoshssilva.desafiodev.services.exceptions.NotFoundException;
import br.com.marcoshssilva.desafiodev.services.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorModel> validationError(ValidationException e, HttpServletRequest r) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ErrorModel errorModel = new ErrorModel(status.value(), e.getMessage(), r.getContextPath());

        return ResponseEntity.status(status).body(errorModel);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorModel> objectNotFound(NotFoundException e, HttpServletRequest r) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorModel errorModel = new ErrorModel(status.value(), e.getMessage(), r.getContextPath());

        return ResponseEntity.status(status).body(errorModel);
    }

    @ExceptionHandler(NoIdEntityException.class)
    public ResponseEntity<ErrorModel> noIDOnEntityObject(NoIdEntityException e, HttpServletRequest r) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ErrorModel errorModel = new ErrorModel(status.value(), e.getMessage(), r.getContextPath());

        return ResponseEntity.status(status).body(errorModel);
    }
}
