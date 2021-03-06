package com.gmail.andersoninfonet.springboot2essentials.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.gmail.andersoninfonet.springboot2essentials.model.Anime;
import com.gmail.andersoninfonet.springboot2essentials.request.AnimeRequestPost;
import com.gmail.andersoninfonet.springboot2essentials.request.AnimeRequestPut;
import com.gmail.andersoninfonet.springboot2essentials.service.AnimeService;
import com.gmail.andersoninfonet.springboot2essentials.util.DateUtil;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * <p>
 * AnimeController class.
 * </p>
 *
 * @author andysteel
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/animes")
@Log4j2
@RequiredArgsConstructor
public class AnimeController {

  /**
  * The {@link com.gmail.andersoninfonet.springboot2essentials.util.DateUtil}
  * property.
  */
  private final DateUtil dateUtil;

  /**
  * The
  * {@link com.gmail.andersoninfonet.springboot2essentials.service.AnimeService}
  * property.
  */
  private final AnimeService service;

  /**
   * Method to return a list os Animes.
   *
   * @return ResponseEntity< Page< Anime > >
   * @param pageable a {@link org.springframework.data.domain.Pageable} object.
   */
  @GetMapping
  @Operation(summary = "List all animes")
  public ResponseEntity<Page<Anime>> list(@ParameterObject Pageable pageable) {
    //comented to not break the UnitTests
    //log.info(dateUtil.formatLocalDateTimeToDataBaseStyle(LocalDateTime.now()));
    return ResponseEntity.ok(service.list(pageable));
  }

  /**
   * Method to find an Anime by Id.
   *
   * @param id long
   * @return ResponseEntity< Anime >
   */
  @GetMapping(path = "/{id}")
  public ResponseEntity<Anime> findById(@PathVariable long id) {
    return ResponseEntity.ok(service.findById(id));
  }

  /**
   * Method to find an Anime by name.
   *
   * @param name String
   * @return ResponseEntity< List< Anime > >
   */
  @GetMapping(path = "/find")
  public ResponseEntity<List<Anime>> findByName(@RequestParam String name) {
    return ResponseEntity.ok(service.listByName(name));
  }

  /**
   * Method to save an Anime.
   *
   * @param anime AnimeRequestPost
   * @return ResponseEntity< Anime >
   */
  @PostMapping
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Created"),
    @ApiResponse(responseCode = "500", description = "Internal server Error")
  })
  public ResponseEntity<Anime> save(@Valid @RequestBody AnimeRequestPost anime) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(anime));
  }

  /**
   * Method to delete an Anime.
   *
   * @param id long
   * @return ResponseEntity< Void >
   */
  @DeleteMapping(path = "/admin/{id}")
  public ResponseEntity<Void> delete(@PathVariable long id) {
    service.delete(id);
    return ResponseEntity.ok().build();
  }

  /**
   * Method to update an Anime.
   *
   * @param anime AnimeRequestPut
   * @return ResponseEntity< Anime >
   */
  @PutMapping
  public ResponseEntity<Void> replace(@Valid @RequestBody AnimeRequestPut anime) {
    service.replace(anime);
    return ResponseEntity.ok().build();
  }

  /**
   * <p>teste.</p>
   *
   * @param request a {@link javax.servlet.http.HttpServletRequest} object.
   */
  @GetMapping(path = "teste")
  public void teste(HttpServletRequest request) {
    log.info(request.getRemoteHost() + "-" + request.getRemoteAddr()
        + "-" + request.getLocalName() + "-" + request.getHeader("Origin")
        + "-" + request.getHeader("User-Agent"));
  }
}
