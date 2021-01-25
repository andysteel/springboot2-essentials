package com.gmail.andersoninfonet.springboot2essentials.request;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnimeRequestPost {

  @NotEmpty(message = "the Anime name can not be empty")
  private String name;
}
