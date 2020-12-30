package com.gmail.andersoninfonet.springboot2essentials.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AnimeRequestPost {

    @NotEmpty(message = "the Anime name can not be empty")
    @NotNull(message = "the Anime name can not be null")
    @NotBlank(message = "the Anime name can not be blank")
    private String name;
}
