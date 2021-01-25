package com.gmail.andersoninfonet.springboot2essentials.service;

import com.gmail.andersoninfonet.springboot2essentials.model.Anime;
import com.gmail.andersoninfonet.springboot2essentials.repository.AnimeRepository;
import com.gmail.andersoninfonet.springboot2essentials.util.AnimeCreator;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class AnimeServiceTest {

  @InjectMocks
  private AnimeService service;

  @Mock
  private AnimeRepository repository;

  @BeforeEach
  public void setup() {
    PageImpl<Anime> animePage = new PageImpl<>(List.of(AnimeCreator.createValidAnime()));
    BDDMockito.when(repository.findAll(ArgumentMatchers.any(PageRequest.class)))
              .thenReturn(animePage);

    BDDMockito.when(repository.findById(ArgumentMatchers.anyLong()))
              .thenReturn(Optional.of(AnimeCreator.createValidAnime()));

    BDDMockito.when(repository.findByName(ArgumentMatchers.anyString()))
              .thenReturn(List.of(AnimeCreator.createValidAnime()));

    BDDMockito.when(repository.save(ArgumentMatchers.any(Anime.class)))
              .thenReturn(AnimeCreator.createValidAnime());

    BDDMockito.doNothing().when(repository).delete(ArgumentMatchers.any(Anime.class));
  }

  @Test
  public void list_ReturnsListOfAnime_WhenSuccessful() {
    String expectedName = AnimeCreator.createAnimeToBeSaved().getName();
    Page<Anime> animePage = service.list(PageRequest.of(1, 1));

    Assertions.assertThat(animePage).isNotNull();
    Assertions.assertThat(animePage.toList()).isNotEmpty().hasSize(1);
    Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);
  }

  @Test
  public void findById_ReturnsAnime_WhenSuccessful() {
    Long expectedId = AnimeCreator.createValidAnime().getId();
    Anime anime = service.findById(1L);

    Assertions.assertThat(anime).isNotNull();
    Assertions.assertThat(anime.getId()).isNotNull().isEqualTo(expectedId);
  }

  @Test
  public void findByName_ReturnsAnime_WhenSuccessful() {
    String expectedName = AnimeCreator.createValidAnime().getName();
    List<Anime> animes = service.listByName("Luciana");

    Assertions.assertThat(animes).isNotNull();
    Assertions.assertThat(animes).isNotEmpty().hasSize(1);
    Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);
  }

  @Test
  public void findByName_ReturnsEmptyListAnime_WhenNotFound() {
    BDDMockito.when(service.listByName(ArgumentMatchers.anyString()))
              .thenReturn(Collections.emptyList());
    List<Anime> animes = service.listByName("xpto");

    Assertions.assertThat(animes).isNotNull().isEmpty();;
  }

  @Test
  public void save_ReturnsAnime_WhenSuccessful() {
    Anime anime = service.save(AnimeCreator.createAnimeRequestPost());

    Assertions.assertThat(anime).isNotNull().isEqualTo(AnimeCreator.createValidAnime());
  }

  @Test
  public void update_ReturnsVoid_WhenSuccessful() {

    Assertions.assertThatCode(() -> service.replace(AnimeCreator.createAnimeRequestPut()))
              .doesNotThrowAnyException();
  }

  @Test
  public void delete_ReturnsVoid_WhenSuccessful() {

    Assertions.assertThatCode(() -> service.delete(1L))
              .doesNotThrowAnyException();
  }
}
