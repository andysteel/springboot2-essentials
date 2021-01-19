package com.gmail.andersoninfonet.springboot2essentials.util;

import com.gmail.andersoninfonet.springboot2essentials.model.Anime;
import com.gmail.andersoninfonet.springboot2essentials.request.AnimeRequestPost;
import com.gmail.andersoninfonet.springboot2essentials.request.AnimeRequestPut;

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

  public static AnimeRequestPost createAnimeRequestPost() {
    return AnimeRequestPost.builder()
            .name(createAnimeToBeSaved().getName())
            .build();
  }

  public static AnimeRequestPut createAnimeRequestPut() {
    return AnimeRequestPut.builder()
            .id(createValidUpdatedAnime().getId())
            .name(createValidUpdatedAnime().getName())
            .build();
  }
}
