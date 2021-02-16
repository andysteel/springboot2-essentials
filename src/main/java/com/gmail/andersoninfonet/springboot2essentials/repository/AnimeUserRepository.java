package com.gmail.andersoninfonet.springboot2essentials.repository;

import com.gmail.andersoninfonet.springboot2essentials.model.AnimeUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * AnimeUserRepository interface.
 * </p>
 *
 * @author andysteel
 * @since 1.0.0
 * @version $Id: $Id
 */
@Repository
public interface AnimeUserRepository extends JpaRepository<AnimeUser, Long> {

  /**
   * <p>
   * findByUsername.
   * </p>
   *
   * @param username a {@link java.lang.String} .
   * @return the AnimeUser .
   */
  AnimeUser findByUsername(String username);
}
