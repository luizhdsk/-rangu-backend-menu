package com.puc.campinas.rangubackendmenu.domain;

import com.puc.campinas.rangubackendmenu.domain.data.RestaurantTableResponse;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "RESTAURANT_TABLE")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantTable {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  private String number;

  private String restaurantId;

  public RestaurantTableResponse toRestaurantTableResponse() {
    return RestaurantTableResponse.builder()
        .id(id)
        .number(number)
        .restaurantId(restaurantId)
        .build();
  }
}
