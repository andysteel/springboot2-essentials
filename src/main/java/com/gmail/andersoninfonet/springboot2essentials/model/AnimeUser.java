package com.gmail.andersoninfonet.springboot2essentials.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>
 * AnimeUser class.
 * </p>
 *
 * @author andysteel
 * @since 1.0.0
 * @version $Id: $Id
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Builder
public class AnimeUser implements UserDetails {

  private static final long serialVersionUID = -4485085192184248590L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty(message = "the  name can not be empty")
  @Column(nullable = false)
  private String name;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "authorities", length = 400)
  private String authorities;

  /** {@inheritDoc} */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Arrays.stream(authorities.split(","))
                  .map(SimpleGrantedAuthority::new)
                  .collect(Collectors.toList());
  }

  /** {@inheritDoc} */
  @Override
  public String getUsername() {
    return this.username;
  }

  /** {@inheritDoc} */
  @Override
  public String getPassword() {
    return this.password;
  }

  /** {@inheritDoc} */
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public boolean isEnabled() {
    return true;
  }

}
