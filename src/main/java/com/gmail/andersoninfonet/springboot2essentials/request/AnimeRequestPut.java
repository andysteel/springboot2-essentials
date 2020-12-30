package com.gmail.andersoninfonet.springboot2essentials.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AnimeRequestPut {
    
    @NotEmpty(message = "the Anime id can not be empty")
    @NotNull(message = "the Anime id can not b null")
    @NotBlank(message = "the Anime id can not be blank")
    private Long id;

    @NotEmpty(message = "the Anime name can not be empty")
    @NotNull(message = "the Anime name can not b null")
    @NotBlank(message = "the Anime name can not be blank")
    private String name;
}
