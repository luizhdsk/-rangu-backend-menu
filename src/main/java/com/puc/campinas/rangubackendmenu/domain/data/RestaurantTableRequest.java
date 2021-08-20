package com.puc.campinas.rangubackendmenu.domain.data;

import com.puc.campinas.rangubackendmenu.domain.RestaurantTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantTableRequest {

  private String number;

  private String restaurantId;

  public RestaurantTable toRestaurantTable() {
    return RestaurantTable.builder()
        .number(number)
        .restaurantId(restaurantId)
        .build();
  }

}
