package com.puc.campinas.rangubackendmenu.domain;

import com.puc.campinas.rangubackendmenu.domain.data.CategoryResponse;
import java.io.Serializable;
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
@Table(name = "CATEGORY")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  private String name;

  private String restaurantId;

  @Builder.Default
  private Boolean isActive = Boolean.TRUE;

  public CategoryResponse toCategoryResponse() {
    return CategoryResponse.builder()
        .name(name)
        .restaurantId(restaurantId)
        .isActive(isActive)
        .build();
  }
}
