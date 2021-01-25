package com.gmail.andersoninfonet.springboot2essentials.wrapper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

/**
 * <p>PageableResponse class.</p>
 *
 * @author andysteel
 * @since 1.0.0
 */
@Getter
@Setter
public class PageableResponse<T> extends PageImpl<T> {

  private boolean first;
  private boolean last;
  private int totalPages;
  private int numberOfElements;

  @JsonCreator(mode = Mode.PROPERTIES )
  /**
   * <p>Constructor for PageableResponse.</p>
   *
   * @param content a {@link java.util.List} object.
   * @param number a int.
   * @param size a int.
   * @param totalElements a int.
   * @param last a boolean.
   * @param first a boolean.
   * @param totalPages a int.
   * @param numberOfElements a int.
   * @param pageable a {@link com.fasterxml.jackson.databind.JsonNode} object.
   * @param sort a {@link com.fasterxml.jackson.databind.JsonNode} object.
   */
  public PageableResponse(@JsonProperty("content") List<T> content,
        @JsonProperty("number") int number,
        @JsonProperty("size") int size,
        @JsonProperty("totalElements") int totalElements,
        @JsonProperty("last") boolean last,
        @JsonProperty("first") boolean first,
        @JsonProperty("totalPages") int totalPages,
        @JsonProperty("numberOfElements") int numberOfElements,
        @JsonProperty("pageable") JsonNode pageable,
        @JsonProperty("sort") JsonNode sort) {
    super(content, PageRequest.of(number, size), totalElements);

    this.last = last;
    this.first = first;
    this.totalPages = totalPages;
    this.numberOfElements = numberOfElements;

  }
}
