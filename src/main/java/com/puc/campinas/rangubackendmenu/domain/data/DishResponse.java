package com.puc.campinas.rangubackendmenu.domain.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.puc.campinas.rangubackendmenu.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DishResponse {

  private String id;

  private String name;

  @JsonInclude(Include.NON_NULL)
  private StringBuilder image;

  @JsonInclude(Include.NON_NULL)
  private String description;

  private Double price;

  private String category;

  private String estimatedTime;

}
