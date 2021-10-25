package com.puc.campinas.rangubackendmenu.domain.data;

import com.puc.campinas.rangubackendmenu.config.Messages;
import com.puc.campinas.rangubackendmenu.domain.Dish;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

  private String image;

  private String description = "No description";

  @NotBlank(message = Messages.CATEGORY_IS_INVALID)
  private String category;

  @NotNull(message = Messages.PRICE_IS_REQUIRED)
  private Double price;

  @NotBlank(message = Messages.ESTIMATED_TIME_IS_REQUIRED)
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
