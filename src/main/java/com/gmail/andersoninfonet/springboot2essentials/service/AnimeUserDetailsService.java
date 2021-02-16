package com.gmail.andersoninfonet.springboot2essentials.service;

import java.util.Optional;

import com.gmail.andersoninfonet.springboot2essentials.repository.AnimeUserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * <p>AnimeUserDetailsService class.</p>
 *
 * @author andysteel
 * @version $Id: $Id
 */
@Service
@RequiredArgsConstructor
public class AnimeUserDetailsService implements UserDetailsService {

  private final AnimeUserRepository animeUserRespository;

  /** {@inheritDoc} */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return Optional.ofNullable(animeUserRespository.findByUsername(username))
            .orElseThrow(() -> new UsernameNotFoundException("Anime User not found"));
  }

}
