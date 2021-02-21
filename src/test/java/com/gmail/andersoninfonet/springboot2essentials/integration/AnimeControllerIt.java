package com.gmail.andersoninfonet.springboot2essentials.integration;

import com.gmail.andersoninfonet.springboot2essentials.model.Anime;
import com.gmail.andersoninfonet.springboot2essentials.model.AnimeUser;
import com.gmail.andersoninfonet.springboot2essentials.repository.AnimeRepository;
import com.gmail.andersoninfonet.springboot2essentials.repository.AnimeUserRepository;
import com.gmail.andersoninfonet.springboot2essentials.request.AnimeRequestPost;
import com.gmail.andersoninfonet.springboot2essentials.util.AnimeCreator;
import com.gmail.andersoninfonet.springboot2essentials.wrapper.PageableResponse;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
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
  @Qualifier(value = "testRestTemplateRoleUser")
  private TestRestTemplate testRestTemplateRoleUser;

  @Autowired
  @Qualifier(value = "testRestTemplateRoleAdmin")
  private TestRestTemplate testRestTemplateRoleAdmin;

  @Autowired
  private AnimeRepository animeRepository;

  @Autowired
  private AnimeUserRepository userRepository;

  private static final AnimeUser ADMIN = AnimeUser.builder()
            .name("Anderson Dias")
            .password("{bcrypt}$2a$10$hSTIR1LEGbkA6US1B0IJVeoTsHrFKzPwXSeE40SvIFckopmMHoUTm")
            .username("anderson2")
            .authorities("ROLE_USER,ROLE_ADMIN")
            .build();

  @TestConfiguration
  @Lazy
  static class TesteConfiguration {

    @Bean(name = "testRestTemplateRoleUser")
    public TestRestTemplate testRestTemplateRoleUser(@Value("${local.server.port}") int port) {
      RestTemplateBuilder builder = new RestTemplateBuilder()
        .rootUri("http://localhost:"+port)
        .basicAuthentication("luciana2", "12345678");
        return new TestRestTemplate(builder);
    }

    @Bean(name = "testRestTemplateRoleAdmin")
    public TestRestTemplate testRestTemplateRoleAdmin(@Value("${local.server.port}") int port) {
      RestTemplateBuilder builder = new RestTemplateBuilder()
        .rootUri("http://localhost:"+port)
        .basicAuthentication("anderson2", "12345678");
        return new TestRestTemplate(builder);
    }
  }

  @Test
  public void list_ReturnsListOfAnime_WhenSuccessful() {
    Anime savedAnime =  animeRepository.save(AnimeCreator.createAnimeToBeSaved());
    String expectedName = savedAnime.getName();
    PageableResponse<Anime> animePage =
    testRestTemplateRoleUser.exchange("/animes", HttpMethod.GET, null,
              new ParameterizedTypeReference<PageableResponse<Anime>>(){}).getBody();

    Assertions.assertThat(animePage).isNotNull();
    Assertions.assertThat(animePage.toList()).isNotEmpty().hasSize(1);
    Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);
  }

  @Test
  public void findById_ReturnsAnime_WhenSuccessful() {
    Anime savedAnime =  animeRepository.save(AnimeCreator.createAnimeToBeSaved());
    Long expectedId = savedAnime.getId();
    Anime anime = testRestTemplateRoleUser.getForObject("/animes/{id}", Anime.class, expectedId);

    Assertions.assertThat(anime).isNotNull();
    Assertions.assertThat(anime.getId()).isNotNull().isEqualTo(expectedId);
  }

  @Test
  public void findByName_ReturnsAnime_WhenSuccessful() {
    Anime savedAnime =  animeRepository.save(AnimeCreator.createAnimeToBeSaved());
    String expectedName = savedAnime.getName();
    String url = String.format("/animes/find?name=%s", expectedName);
    List<Anime> animes =
    testRestTemplateRoleUser.exchange(url, HttpMethod.GET, null,
          new ParameterizedTypeReference<List<Anime>>(){}).getBody();

    Assertions.assertThat(animes).isNotNull();
    Assertions.assertThat(animes).isNotEmpty().hasSize(1);
    Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);
  }

  @Test
  public void findByName_ReturnsEmptyListAnime_WhenNotFound() {
    List<Anime> animes =
    testRestTemplateRoleUser.exchange("/animes/find?name=xpto", HttpMethod.GET, null,
          new ParameterizedTypeReference<List<Anime>>(){}).getBody();

    Assertions.assertThat(animes).isNotNull().isEmpty();;
  }

  @Test
  public void save_ReturnsAnime_WhenSuccessful() {
    AnimeRequestPost requestPost = AnimeCreator.createAnimeRequestPost();
    ResponseEntity<Anime> anime =
    testRestTemplateRoleUser.postForEntity("/animes", requestPost, Anime.class);

    Assertions.assertThat(anime).isNotNull();
    Assertions.assertThat(anime.getBody()).isNotNull().isEqualTo(AnimeCreator.createValidAnime());
    Assertions.assertThat(anime.getStatusCode()).isEqualTo(HttpStatus.CREATED);
  }

  @Test
  public void update_ReturnsVoid_WhenSuccessful() {
    Anime savedAnime =  animeRepository.save(AnimeCreator.createAnimeToBeSaved());
    savedAnime.setName("new name");
    ResponseEntity<Void> response =
    testRestTemplateRoleUser.exchange("/animes", HttpMethod.PUT,
          new HttpEntity<>(savedAnime), Void.class);

    Assertions.assertThat(response).isNotNull();
    Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  public void delete_ReturnsVoid_WhenSuccessful() {
    userRepository.save(ADMIN);
    Anime savedAnime =  animeRepository.save(AnimeCreator.createAnimeToBeSaved());
    ResponseEntity<Void> response =
    testRestTemplateRoleAdmin.exchange("/animes/admin/{id}", HttpMethod.DELETE,
          null, Void.class,  savedAnime.getId());

    Assertions.assertThat(response).isNotNull();
    Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  public void delete_Returns403_WhenUserIsNotAdmin() {
    Anime savedAnime =  animeRepository.save(AnimeCreator.createAnimeToBeSaved());
    ResponseEntity<Void> response =
    testRestTemplateRoleUser.exchange("/animes/admin/{id}", HttpMethod.DELETE,
          null, Void.class,  savedAnime.getId());

    Assertions.assertThat(response).isNotNull();
    Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
  }
}
