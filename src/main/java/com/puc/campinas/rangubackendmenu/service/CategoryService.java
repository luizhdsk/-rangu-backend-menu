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

  public Category saveCategory(Category category) {
    if (!existsCategory(category)) {
      return categoryRepository.save(category);
    } else {
      throw new CategoryException(Messages.CATEGORY_ALREADY_EXISTS);
    }
  }

  private boolean existsCategory(Category category) {
    return categoryRepository.existsByNameAndRestaurantId(category.getName(),
        category.getRestaurantId());
  }

  public void validCategory(String category, String restaurantId) {
    categoryRepository.findByNameAndRestaurantId(category, restaurantId).orElseThrow(() -> {
      throw new CategoryException(Messages.RESTAURANT_OR_CATEGORY_NOT_FOUND);
    });
  }
}
