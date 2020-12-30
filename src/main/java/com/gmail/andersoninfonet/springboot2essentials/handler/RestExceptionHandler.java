package com.gmail.andersoninfonet.springboot2essentials.handler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.gmail.andersoninfonet.springboot2essentials.exception.AnimeBadRequestException;
import com.gmail.andersoninfonet.springboot2essentials.exception.AnimeValidationExceptionDetails;
import com.gmail.andersoninfonet.springboot2essentials.exception.ExceptionDetails;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(AnimeBadRequestException.class)
    public ResponseEntity<ExceptionDetails> handlerAnimeBadRequestException(AnimeBadRequestException ex) {
        return new ResponseEntity<>(
            ExceptionDetails.builder()
                            .title("Anime Bad Request exception.")
                            .status(HttpStatus.BAD_REQUEST.value())
                            .className(ex.getClass().getName())
                            .details(ex.getMessage())
                            .timestamp(Instant.now()).build(),
            HttpStatus.BAD_REQUEST
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<AnimeValidationExceptionDetails> validations = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(e -> {
            validations.add(AnimeValidationExceptionDetails.builder()
                                                        .field(e.getField())
                                                        .fieldMessage(e.getDefaultMessage())
                                                        .build());
        });
        return new ResponseEntity<>(
            ExceptionDetails.builder()
                            .title("Anime Bad Request exception.")
                            .status(HttpStatus.BAD_REQUEST.value())
                            .className(ex.getClass().getName())
                            .details("Anime validation errors.")
                            .timestamp(Instant.now())
                            .validations(validations).build(),
            HttpStatus.BAD_REQUEST
        );
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
        Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
            .title(ex.getCause().getMessage())
            .status(status.value())
            .className(ex.getClass().getName())
            .details(ex.getMessage())
            .timestamp(Instant.now()).build();
    return new ResponseEntity<>(exceptionDetails, headers, status);
    }
}
