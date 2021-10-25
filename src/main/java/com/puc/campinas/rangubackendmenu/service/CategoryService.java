package com.puc.campinas.rangubackendmenu.service;

import com.puc.campinas.rangubackendmenu.config.Messages;
import com.puc.campinas.rangubackendmenu.config.exception.CategoryException;
import com.puc.campinas.rangubackendmenu.config.exception.DishException;
import com.puc.campinas.rangubackendmenu.domain.Category;
import com.puc.campinas.rangubackendmenu.domain.Dish;
import com.puc.campinas.rangubackendmenu.domain.data.CategoryResponse;
import com.puc.campinas.rangubackendmenu.domain.data.DishResponse;
import com.puc.campinas.rangubackendmenu.repository.CategoryRepository;
import java.util.Collection;
import java.util.stream.Collectors;
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

  public Collection<CategoryResponse> getCategories(String restaurantId) {
    var dishes = categoryRepository.findByRestaurantId(restaurantId)
        .orElseThrow(() -> new CategoryException(
            Messages.RESTAURANT_NOT_FOUND));
    return dishes.stream().map(Category::toCategoryResponse).collect(Collectors.toList());
  }
}
