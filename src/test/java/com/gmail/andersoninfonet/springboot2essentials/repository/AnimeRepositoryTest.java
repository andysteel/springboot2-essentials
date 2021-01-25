package com.gmail.andersoninfonet.springboot2essentials.repository;

import com.gmail.andersoninfonet.springboot2essentials.model.Anime;
import com.gmail.andersoninfonet.springboot2essentials.util.AnimeCreator;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import javax.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Tests for Anime repository")
public class AnimeRepositoryTest {

  @Autowired
  private AnimeRepository repository;

  @Test
  public void save_PersistAnime_WhenSuccessful() {
    Anime anime = AnimeCreator.createAnimeToBeSaved();
    Anime savedAnime = this.repository.save(anime);
    Assertions.assertNotNull(savedAnime);
    Assertions.assertNotNull(savedAnime.getId());
    Assertions.assertEquals(anime.getName(), savedAnime.getName());
  }

  @Test
  public void save_UpdateAnime_WhenSuccessful() {
    Anime animeToBeSaved = AnimeCreator.createAnimeToBeSaved();
    Anime savedAnime = this.repository.save(animeToBeSaved);
    savedAnime.setName("Anderson");
    Anime updatedAnime = this.repository.save(savedAnime);
    Assertions.assertNotNull(updatedAnime);
    Assertions.assertNotNull(updatedAnime.getId());
    Assertions.assertEquals(savedAnime.getName(), updatedAnime.getName());
  }

  @Test
  public void delete_RemovesAnime_WhenSuccessful() {
    Anime anime = AnimeCreator.createAnimeToBeSaved();
    this.repository.save(anime);
    this.repository.delete(anime);

    Assertions.assertFalse(this.repository.existsById(anime.getId()));
  }


  @Test
  public void findByName_ReturnListOfAnime_WhenSuccessful() {
    Anime anime = AnimeCreator.createAnimeToBeSaved();
    Anime savedAnime = this.repository.save(anime);
    String name = savedAnime.getName();
    List<Anime> animes = this.repository.findByName(name);

    Assertions.assertNotNull(animes);
    org.assertj.core.api.Assertions.assertThat(animes).isNotEmpty();
    org.assertj.core.api.Assertions.assertThat(animes).contains(savedAnime);
  }

  @Test
  public void findByName_ReturnEmptyListOfAnime_WhenAnimeNotFound() {
    List<Anime> animes = this.repository.findByName("DUDU");

    org.assertj.core.api.Assertions.assertThat(animes).isEmpty();
  }

  @Test
  public void save_ThrowConstraintViolationException_WhenNameIsEmpty() {
    Anime anime = new Anime();
    //org.assertj.core.api.Assertions.assertThatThrownBy(() -> this.repository.save(anime))
    //                                .isInstanceOf(ConstraintViolationException.class);

    //second way
    org.assertj.core.api.Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                                    .isThrownBy(() -> this.repository.save(anime))
                                    .withMessageContaining("the Anime name can not be empty");

  }

}
