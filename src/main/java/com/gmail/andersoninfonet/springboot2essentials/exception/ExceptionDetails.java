package com.gmail.andersoninfonet.springboot2essentials.exception;

import java.time.Instant;
import java.util.List;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * ExceptionDetails class.
 * </p>
 *
 * @author andysteel
 * @version 1.0.0
 */
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
