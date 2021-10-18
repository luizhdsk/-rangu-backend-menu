package com.puc.campinas.rangubackendmenu.repository;

import com.google.common.base.Strings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StringSetConverter implements AttributeConverter<Set<String>, String> {

  @Override
  public String convertToDatabaseColumn(Set<String> list) {
      return Objects.isNull(list) ? null : String.join(",", list);
  }

  @Override
  public Set<String> convertToEntityAttribute(String joined) {
    return Strings.isNullOrEmpty(joined) ? new HashSet<>() : new HashSet<>(Arrays.asList(joined.split(",")));
  }

}
