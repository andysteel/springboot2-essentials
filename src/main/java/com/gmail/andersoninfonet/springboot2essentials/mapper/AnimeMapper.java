package com.gmail.andersoninfonet.springboot2essentials.mapper;

import com.gmail.andersoninfonet.springboot2essentials.model.Anime;
import com.gmail.andersoninfonet.springboot2essentials.request.AnimeRequestPost;
import com.gmail.andersoninfonet.springboot2essentials.request.AnimeRequestPut;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * Abstract AnimeMapper class.
 * </p>
 *
 * @author andysteel
 * @version 1.0.0
 * @since 1.0.0
 */
@Mapper(componentModel = "spring")
public abstract class AnimeMapper {

  /** Constant <code>INSTANCE</code>. */
  public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

  /**
   * <p>
   * fromPostToAnime.
   * </p>
   *
   * @param requestPost a
   *       {@link com.gmail.andersoninfonet.springboot2essentials.request.AnimeRequestPost}.
   * @return a {@link com.gmail.andersoninfonet.springboot2essentials.model.Anime}
   *         object.
   */
  public abstract Anime fromPostToAnime(AnimeRequestPost requestPost);

  /**
   * <p>
   * fromPutToAnime.
   * </p>
   *
   * @param requestPut a
   *       {@link com.gmail.andersoninfonet.springboot2essentials.request.AnimeRequestPut}.
   * @return a {@link com.gmail.andersoninfonet.springboot2essentials.model.Anime}
   *         object.
   */
  public abstract Anime fromPutToAnime(AnimeRequestPut requestPut);
}
