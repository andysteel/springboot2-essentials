package com.gmail.andersoninfonet.springboot2essentials.exception;

import lombok.Builder;
import lombok.Data;

/**
 * <p>
 * AnimeValidationExceptionDetails class.
 * </p>
 *
 * @author andysteel
 * @version 1.0.0
 */
@Data
@Builder
public class AnimeValidationExceptionDetails {

  private final String field;
  private final String fieldMessage;
}
