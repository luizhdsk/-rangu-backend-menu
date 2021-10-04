package com.puc.campinas.rangubackendmenu.domain.data;

import com.puc.campinas.rangubackendmenu.config.Messages;
import com.puc.campinas.rangubackendmenu.domain.RestaurantTable;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantTableRequest {

  @NotBlank(message = Messages.NUMBER_ID_IS_REQUIRED)
  private String number;

  public RestaurantTable toRestaurantTable(String restaurantId) {
    return RestaurantTable.builder()
        .restaurantId(restaurantId)
        .number(number)
        .clientTableId(null).build();
  }
}
