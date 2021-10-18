package com.puc.campinas.rangubackendmenu.domain.data;

import com.puc.campinas.rangubackendmenu.config.Messages;
import com.puc.campinas.rangubackendmenu.domain.Category;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {

  @NotBlank(message = Messages.NAME_IS_REQUIRED)
  private String name;

  public Category toCategory(String restaurantId) {
    return Category.builder()
        .name(name)
        .restaurantId(restaurantId)
        .isActive(Boolean.TRUE)
        .build();
  }

}
