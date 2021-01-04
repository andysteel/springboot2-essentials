package com.gmail.andersoninfonet.springboot2essentials.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * AnimeRequestPut class.
 * </p>
 *
 * @author andysteel
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class AnimeRequestPut {

  @NotNull(message = "the Anime id can not be null")
  private Long id;

  @NotEmpty(message = "the Anime name can not be empty")
  @NotNull(message = "the Anime name can not be null")
  @NotBlank(message = "the Anime name can not be blank")
  private String name;
}
