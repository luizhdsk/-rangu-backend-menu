package com.puc.campinas.rangubackendmenu.repository;

import com.google.common.base.Strings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

  @Override
  public String convertToDatabaseColumn(List<String> list) {
      return Objects.isNull(list) ? null : String.join(",", list);
  }

  @Override
  public List<String> convertToEntityAttribute(String joined) {
    return Strings.isNullOrEmpty(joined) ? new ArrayList<>() : new ArrayList<>(Arrays.asList(joined.split(",")));
  }

}
