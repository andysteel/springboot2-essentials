package com.gmail.andersoninfonet.springboot2essentials.config;

import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>Spring2WebMVCConfig class.</p>
 *
 * @author andysteel
 * @version 1.0.0
 * @since 1.0.0
 */
@Configuration
public class Spring2WebMvcConfig implements WebMvcConfigurer {

  /** {@inheritDoc} */
  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {

    PageableHandlerMethodArgumentResolver pageHandler =
        new PageableHandlerMethodArgumentResolver();
    pageHandler.setFallbackPageable(PageRequest.of(0, 5));
    resolvers.add(pageHandler);
  }
}
