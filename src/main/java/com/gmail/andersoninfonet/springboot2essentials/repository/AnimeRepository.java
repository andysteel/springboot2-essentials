package com.gmail.andersoninfonet.springboot2essentials.repository;

import com.gmail.andersoninfonet.springboot2essentials.model.Anime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * AnimeRepository interface.
 * </p>
 *
 * @author andysteel
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long> {

  /**
   * <p>
   * findByName.
   * </p>
   *
   * @param name a {@link java.lang.String} .
   * @return a {@link java.util.List} .
   */
  List<Anime> findByName(String name);
}
