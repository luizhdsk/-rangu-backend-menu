package com.puc.campinas.rangubackendmenu.domain;

import java.util.Arrays;

public enum Category {
  HAMBURGER("HAMBURGER"),
  PIZZA("PIZZA"),
  MEAT("MEAT"),
  CHINESE("CHINESE"),
  JAPANESE("JAPANESE"),
  DRINKS("DRINKS"),
  ITALIAN("ITALIAN"),
  DESSERT("DESSERT");

  private String code;

  Category(String code){this.code = code;}

  public String code(){return code;}

  public static Category from(String code) {
    return Arrays.stream(Category.values())
        .filter(it -> it.code.equals(code))
        .findFirst().orElse(null);
  }

}
