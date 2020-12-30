package com.gmail.andersoninfonet.springboot2essentials.exception;

import java.time.Instant;
import java.util.List;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ExceptionDetails {
    
    private String title;
    private int status;
    private String details;
    private String className;
    private Instant timestamp;
    private List<AnimeValidationExceptionDetails> validations;
}
