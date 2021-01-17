package com.gmail.andersoninfonet.springboot2essentials.util;

import com.gmail.andersoninfonet.springboot2essentials.model.Anime;

public class AnimeCreator {

  public static Anime createAnimeToBeSaved() {
    return Anime.builder()
            .name("Luciana")
            .build();
  }

  public static Anime createValidAnime() {
    return Anime.builder()
            .name("Luciana")
            .id(1L)
            .build();
  }

  public static Anime createValidUpdatedAnime() {
    return Anime.builder()
            .name("Luciana 2")
            .id(1L)
            .build();
  }
}
