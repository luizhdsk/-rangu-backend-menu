package com.puc.campinas.rangubackendmenu.service;

import com.puc.campinas.rangubackendmenu.config.Messages;
import com.puc.campinas.rangubackendmenu.config.exception.CategoryException;
import com.puc.campinas.rangubackendmenu.domain.Category;
import com.puc.campinas.rangubackendmenu.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {

  private CategoryRepository categoryRepository;

  public Category saveCategory(String restaurantId, String name) {
    var category = Category.builder()
        .name(name)
        .restaurantId(restaurantId)
        .isActive(Boolean.TRUE)
        .build();
    return categoryRepository.save(category);
  }

  public void validCategory(String category, String restaurantId) {
    categoryRepository.findByNameAndRestaurantId(category, restaurantId).orElseThrow(() -> {
      throw new CategoryException(Messages.RESTAURANT_OR_CATEGORY_NOT_FOUND);
    });
  }
}
