package com.gmail.andersoninfonet.springboot2essentials.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

/**
 * <p>
 * DateUtil class.
 * </p>
 *
 * @author andysteel
 * @version 1.0.0
 */
@Component
public class DateUtil {

  /**
   * <p>
   * formatLocalDateTimeToDataBaseStyle.
   * </p>
   *
   * @param localDateTime a {@link java.time.LocalDateTime} object.
   * @return a {@link java.lang.String} object.
   */
  public String formatLocalDateTimeToDataBaseStyle(LocalDateTime localDateTime) {
    return DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss").format(localDateTime);
  }
}
