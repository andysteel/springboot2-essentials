package com.gmail.andersoninfonet.springboot2essentials.config;

import com.gmail.andersoninfonet.springboot2essentials.service.AnimeUserDetailsService;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * <p>SecurityConfig class.</p>
 *
 * @author andysteel
 * @since 1.0.0
 * @version $Id: $Id
 */
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final AnimeUserDetailsService animeUserDetailsService;

  /** {@inheritDoc} */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
    //config for production
    //.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
    //.and()
        .authorizeRequests()
        .antMatchers("/animes/admin/**").hasRole("ADMIN")
        .antMatchers("/animes/**").hasRole("USER")
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic();
  }

  /** {@inheritDoc} */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    auth.inMemoryAuthentication()
        .withUser("anderson2")
        .password(encoder.encode("12345678"))
        .roles("USER", "ADMIN")
        .and()
        .withUser("luciana2")
        .password(encoder.encode("12345678"))
        .roles("USER");
    auth.userDetailsService(animeUserDetailsService).passwordEncoder(encoder);
  }

}
