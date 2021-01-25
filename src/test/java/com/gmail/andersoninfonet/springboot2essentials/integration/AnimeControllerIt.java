package com.gmail.andersoninfonet.springboot2essentials.integration;

import com.gmail.andersoninfonet.springboot2essentials.model.Anime;
import com.gmail.andersoninfonet.springboot2essentials.repository.AnimeRepository;
import com.gmail.andersoninfonet.springboot2essentials.request.AnimeRequestPost;
import com.gmail.andersoninfonet.springboot2essentials.util.AnimeCreator;
import com.gmail.andersoninfonet.springboot2essentials.wrapper.PageableResponse;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AnimeControllerIt {

  @Autowired
  private TestRestTemplate testRestTemplate;

  @LocalServerPort
  private int port;

  @Autowired
  private AnimeRepository animeRepository;

  @Test
  public void list_ReturnsListOfAnime_WhenSuccessful() {
    Anime savedAnime =  animeRepository.save(AnimeCreator.createAnimeToBeSaved());
    String expectedName = savedAnime.getName();
    PageableResponse<Anime> animePage =
            testRestTemplate.exchange("/animes", HttpMethod.GET, null,
              new ParameterizedTypeReference<PageableResponse<Anime>>(){}).getBody();

    Assertions.assertThat(animePage).isNotNull();
    Assertions.assertThat(animePage.toList()).isNotEmpty().hasSize(1);
    Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);
  }

  @Test
  public void findById_ReturnsAnime_WhenSuccessful() {
    Anime savedAnime =  animeRepository.save(AnimeCreator.createAnimeToBeSaved());
    Long expectedId = savedAnime.getId();
    Anime anime = testRestTemplate.getForObject("/animes/{id}", Anime.class, expectedId);

    Assertions.assertThat(anime).isNotNull();
    Assertions.assertThat(anime.getId()).isNotNull().isEqualTo(expectedId);
  }

  @Test
  public void findByName_ReturnsAnime_WhenSuccessful() {
    Anime savedAnime =  animeRepository.save(AnimeCreator.createAnimeToBeSaved());
    String expectedName = savedAnime.getName();
    String url = String.format("/animes/find?name=%s", expectedName);
    List<Anime> animes =
        testRestTemplate.exchange(url, HttpMethod.GET, null,
          new ParameterizedTypeReference<List<Anime>>(){}).getBody();

    Assertions.assertThat(animes).isNotNull();
    Assertions.assertThat(animes).isNotEmpty().hasSize(1);
    Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);
  }

  @Test
  public void findByName_ReturnsEmptyListAnime_WhenNotFound() {
    List<Anime> animes =
        testRestTemplate.exchange("/animes/find?name=xpto", HttpMethod.GET, null,
          new ParameterizedTypeReference<List<Anime>>(){}).getBody();

    Assertions.assertThat(animes).isNotNull().isEmpty();;
  }

  @Test
  public void save_ReturnsAnime_WhenSuccessful() {
    AnimeRequestPost requestPost = AnimeCreator.createAnimeRequestPost();
    ResponseEntity<Anime> anime =
        testRestTemplate.postForEntity("/animes", requestPost, Anime.class);

    Assertions.assertThat(anime).isNotNull();
    Assertions.assertThat(anime.getBody()).isNotNull().isEqualTo(AnimeCreator.createValidAnime());
    Assertions.assertThat(anime.getStatusCode()).isEqualTo(HttpStatus.CREATED);
  }

  @Test
  public void update_ReturnsVoid_WhenSuccessful() {
    Anime savedAnime =  animeRepository.save(AnimeCreator.createAnimeToBeSaved());
    savedAnime.setName("new name");
    ResponseEntity<Void> response =
        testRestTemplate.exchange("/animes", HttpMethod.PUT,
          new HttpEntity<>(savedAnime), Void.class);

    Assertions.assertThat(response).isNotNull();
    Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  public void delete_ReturnsVoid_WhenSuccessful() {
    Anime savedAnime =  animeRepository.save(AnimeCreator.createAnimeToBeSaved());
    ResponseEntity<Void> response =
        testRestTemplate.exchange("/animes/{id}", HttpMethod.DELETE,
          null, Void.class,  savedAnime.getId());

    Assertions.assertThat(response).isNotNull();
    Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }
}
