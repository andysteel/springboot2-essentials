package com.gmail.andersoninfonet.springboot2essentials.controller;

import java.util.List;

import com.gmail.andersoninfonet.springboot2essentials.model.Anime;
import com.gmail.andersoninfonet.springboot2essentials.service.AnimeService;
import com.gmail.andersoninfonet.springboot2essentials.util.AnimeCreator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class AnimeControllerTest {

  @InjectMocks
  private AnimeController controller;

  @Mock
  private AnimeService service;

  @BeforeEach
  public void setup() {
    PageImpl<Anime> animePage = new PageImpl<>(List.of(AnimeCreator.createValidAnime()));
    BDDMockito.when(service.list(ArgumentMatchers.any()))
              .thenReturn(animePage);
  }

  @Test
  public void list_ReturnsListOfAnime_WhenSuccessful() {
    String expectedName = AnimeCreator.createAnimeToBeSaved().getName();
    Page<Anime> animePage = controller.list(null).getBody();

    Assertions.assertThat(animePage).isNotNull();
    Assertions.assertThat(animePage.toList()).isNotEmpty().hasSize(1);
    Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);
  }
}
