package com.gmail.andersoninfonet.springboot2essentials.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p>
 * AnimeBadRequestException class.
 * </p>
 *
 * @author andysteel
 * @version 1.0.0
 * @since 1.0.0
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class AnimeBadRequestException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Constructor for AnimeBadRequestException.
   * </p>
   *
   * @param message String {@link java.lang.String}.
   */
  public AnimeBadRequestException(final String message) {
    super(message);
  }

  /**
   * <p>
   * Constructor for AnimeBadRequestException.
   * </p>
   *
   * @param cause a {@link java.lang.Throwable} object.
   */
  public AnimeBadRequestException(final Throwable cause) {
    super(cause);
  }

  /**
   * <p>
   * Constructor for AnimeBadRequestException.
   * </p>
   *
   * @param message a {@link java.lang.String} object.
   * @param cause   a {@link java.lang.Throwable} object.
   */
  public AnimeBadRequestException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
