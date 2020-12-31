package com.gmail.andersoninfonet.springboot2essentials.service;

import com.gmail.andersoninfonet.springboot2essentials.exception.AnimeBadRequestException;
import com.gmail.andersoninfonet.springboot2essentials.mapper.AnimeMapper;
import com.gmail.andersoninfonet.springboot2essentials.model.Anime;
import com.gmail.andersoninfonet.springboot2essentials.repository.AnimeRepository;
import com.gmail.andersoninfonet.springboot2essentials.request.AnimeRequestPost;
import com.gmail.andersoninfonet.springboot2essentials.request.AnimeRequestPut;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * AnimeService class.
 * </p>
 *
 * @author andysteel
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AnimeService {

  private final AnimeRepository animeRepository;

  /**
   * <p>
   * list.
   * </p>
   *
   * @return a {@link java.util.List} object.
   */
  public List<Anime> list() {
    return animeRepository.findAll();
  }

  /**
   * <p>
   * listByName.
   * </p>
   *
   * @param name a {@link java.lang.String} object.
   * @return a {@link java.util.List} object.
   */
  public List<Anime> listByName(String name) {
    return animeRepository.findByName(name);
  }

  /**
   * <p>
   * findById.
   * </p>
   *
   * @param id a long.
   * @return a {@link com.gmail.andersoninfonet.springboot2essentials.model.Anime}
   *         object.
   */
  public Anime findById(long id) {
    return animeRepository.findById(id)
            .orElseThrow(() -> new AnimeBadRequestException("Anime not found."));
  }

  /**
   * <p>
   * save.
   * </p>
   *
   * @param requestPost a
   * {@link com.gmail.andersoninfonet.springboot2essentials.request.AnimeRequestPost}
   *     object.
   * @return a {@link com.gmail.andersoninfonet.springboot2essentials.model.Anime}
   *         object.
   */
  public Anime save(AnimeRequestPost requestPost) {
    return animeRepository.save(AnimeMapper.INSTANCE.fromPostToAnime(requestPost));
  }

  /**
   * <p>
   * delete.
   * </p>
   *
   * @param id a long.
   */
  public void delete(long id) {
    animeRepository.delete(this.findById(id));
  }

  /**
   * <p>
   * replace.
   * </p>
   *
   * @param requestPut a
   * {@link com.gmail.andersoninfonet.springboot2essentials.request.AnimeRequestPut}
   *     object.
   */
  public void replace(AnimeRequestPut requestPut) {
    Anime savedAnime = this.findById(requestPut.getId());
    Anime anime = AnimeMapper.INSTANCE.fromPutToAnime(requestPut);
    anime.setId(savedAnime.getId());
    animeRepository.save(anime);
  }
}
