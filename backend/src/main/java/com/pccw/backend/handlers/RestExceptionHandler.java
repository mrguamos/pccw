package com.pccw.backend.handlers;

import com.pccw.backend.exceptions.ErrorResponse;
import com.pccw.backend.exceptions.PccwException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
        log.error("Runtime Exception Handler", e);
        return ResponseEntity.internalServerError().body(new ErrorResponse("Internal Server Error"));
    }

    @ExceptionHandler(PccwException.class)
    public ResponseEntity<ErrorResponse> handleRestException(PccwException e) {
        log.error("RestException Exception Handler", e);
        return ResponseEntity.unprocessableEntity().body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error("DataIntegrityViolationException Exception Handler", e);
        return ResponseEntity.unprocessableEntity().body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException e) {
        log.error("ValidationException Exception Handler", e);
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }

}
