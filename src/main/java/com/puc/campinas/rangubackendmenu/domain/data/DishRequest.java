package com.puc.campinas.rangubackendmenu.domain.data;

import com.puc.campinas.rangubackendmenu.config.Messages;
import com.puc.campinas.rangubackendmenu.domain.Category;
import com.puc.campinas.rangubackendmenu.domain.Dish;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DishRequest {

  @NotBlank(message = Messages.NAME_IS_REQUIRED)
  private String name;

  private StringBuilder image;

  private String description = "";

  @Enumerated(EnumType.STRING)
  private Category category;

  private Double price;

  private String estimatedTime;


  public Dish toDish() {
    return Dish.builder()
        .name(name)
        .image(image)
        .description(description)
        .price(price)
        .category(category)
        .estimatedTime(estimatedTime)
        .build();
  }
}
