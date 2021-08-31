package com.puc.campinas.rangubackendmenu.domain.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantTableResponse {

  private String id;

  private String number;

  private String restaurantId;

  @JsonInclude(Include.NON_NULL)
  private String clientTableId;

}
