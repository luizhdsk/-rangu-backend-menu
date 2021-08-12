package com.puc.campinas.rangubackendmenu.domain;

import com.puc.campinas.rangubackendmenu.domain.data.DishResponse;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "DISH")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dish implements Serializable {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  private String name;

  private StringBuilder image;

  private String description;

  private String category;

  private Double price;

  private String restaurantId;

  private String estimatedTime;

  public DishResponse toDishResponse() {

    return DishResponse.builder()
        .id(id)
        .name(name)
        .image(image)
        .description(description)
        .category(category)
        .estimatedTime(estimatedTime)
        .build();
  }
}
