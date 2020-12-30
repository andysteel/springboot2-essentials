package com.gmail.andersoninfonet.springboot2essentials.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnimeValidationExceptionDetails {
    
    private final String field;
    private final String fieldMessage;
}
