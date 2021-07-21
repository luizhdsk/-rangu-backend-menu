package com.puc.campinas.rangubackendmenu.domain;

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

}
