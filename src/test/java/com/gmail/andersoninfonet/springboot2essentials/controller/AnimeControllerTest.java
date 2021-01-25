package com.gmail.andersoninfonet.springboot2essentials.controller;

import com.gmail.andersoninfonet.springboot2essentials.model.Anime;
import com.gmail.andersoninfonet.springboot2essentials.request.AnimeRequestPost;
import com.gmail.andersoninfonet.springboot2essentials.request.AnimeRequestPut;
import com.gmail.andersoninfonet.springboot2essentials.service.AnimeService;
import com.gmail.andersoninfonet.springboot2essentials.util.AnimeCreator;
import java.util.Collections;
import java.util.List;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    BDDMockito.when(service.findById(ArgumentMatchers.anyLong()))
              .thenReturn(AnimeCreator.createValidAnime());

    BDDMockito.when(service.listByName(ArgumentMatchers.anyString()))
              .thenReturn(List.of(AnimeCreator.createValidAnime()));

    BDDMockito.when(service.save(ArgumentMatchers.any(AnimeRequestPost.class)))
              .thenReturn(AnimeCreator.createValidAnime());

    BDDMockito.doNothing().when(service).replace(ArgumentMatchers.any(AnimeRequestPut.class));

    BDDMockito.doNothing().when(service).delete(ArgumentMatchers.anyLong());
  }

  @Test
  public void list_ReturnsListOfAnime_WhenSuccessful() {
    String expectedName = AnimeCreator.createAnimeToBeSaved().getName();
    Page<Anime> animePage = controller.list(null).getBody();

    Assertions.assertThat(animePage).isNotNull();
    Assertions.assertThat(animePage.toList()).isNotEmpty().hasSize(1);
    Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);
  }

  @Test
  public void findById_ReturnsAnime_WhenSuccessful() {
    Long expectedId = AnimeCreator.createValidAnime().getId();
    Anime anime = controller.findById(1L).getBody();

    Assertions.assertThat(anime).isNotNull();
    Assertions.assertThat(anime.getId()).isNotNull().isEqualTo(expectedId);
  }

  @Test
  public void findByName_ReturnsAnime_WhenSuccessful() {
    String expectedName = AnimeCreator.createValidAnime().getName();
    List<Anime> animes = controller.findByName("Luciana").getBody();

    Assertions.assertThat(animes).isNotNull();
    Assertions.assertThat(animes).isNotEmpty().hasSize(1);
    Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);
  }

  @Test
  public void findByName_ReturnsEmptyListAnime_WhenNotFound() {
    BDDMockito.when(service.listByName(ArgumentMatchers.anyString()))
              .thenReturn(Collections.emptyList());
    List<Anime> animes = controller.findByName("xpto").getBody();

    Assertions.assertThat(animes).isNotNull().isEmpty();;
  }

  @Test
  public void save_ReturnsAnime_WhenSuccessful() {
    Anime anime = controller.save(AnimeCreator.createAnimeRequestPost()).getBody();

    Assertions.assertThat(anime).isNotNull().isEqualTo(AnimeCreator.createValidAnime());
  }

  @Test
  public void update_ReturnsVoid_WhenSuccessful() {

    Assertions.assertThatCode(() -> controller.replace(AnimeCreator.createAnimeRequestPut()))
              .doesNotThrowAnyException();
    ResponseEntity<Void> response = controller.replace(AnimeCreator.createAnimeRequestPut());

    Assertions.assertThat(response.getStatusCode()).isNotNull().isEqualTo(HttpStatus.OK);
  }

  @Test
  public void delete_ReturnsVoid_WhenSuccessful() {

    Assertions.assertThatCode(() -> controller.delete(1L))
              .doesNotThrowAnyException();
    ResponseEntity<Void> response = controller.delete(1L);

    Assertions.assertThat(response.getStatusCode()).isNotNull().isEqualTo(HttpStatus.OK);
  }
}
