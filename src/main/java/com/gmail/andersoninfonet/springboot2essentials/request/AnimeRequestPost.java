package com.gmail.andersoninfonet.springboot2essentials.request;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * <p>
 * AnimeRequestPost class.
 * </p>
 *
 * @author andysteel
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class AnimeRequestPost {

  @NotEmpty(message = "the Anime name can not be empty")
  private String name;
}
