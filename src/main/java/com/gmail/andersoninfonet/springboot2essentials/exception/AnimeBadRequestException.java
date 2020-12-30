package com.gmail.andersoninfonet.springboot2essentials.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class AnimeBadRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * @param message
     */
    public AnimeBadRequestException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public AnimeBadRequestException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public AnimeBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    
}
