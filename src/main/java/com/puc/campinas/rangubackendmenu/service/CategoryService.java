package com.puc.campinas.rangubackendmenu.service;

import com.puc.campinas.rangubackendmenu.config.Messages;
import com.puc.campinas.rangubackendmenu.config.exception.CategoryException;
import com.puc.campinas.rangubackendmenu.domain.Category;
import com.puc.campinas.rangubackendmenu.domain.data.CategoryResponse;
import com.puc.campinas.rangubackendmenu.repository.CategoryRepository;
import java.util.Collection;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

  private CategoryRepository categoryRepository;

  private DishService dishService;

  public CategoryService(
      CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Autowired
  public void setDishService(DishService dishService) {
    this.dishService = dishService;
  }

  public Category saveCategory(Category category) {
    if (!existsCategory(category.getName(), category.getRestaurantId())) {
      return categoryRepository.save(category);
    } else {
      throw new CategoryException(Messages.CATEGORY_ALREADY_EXISTS);
    }
  }

  private boolean existsCategory(String category, String restaurantId) {
    return categoryRepository.existsByNameAndRestaurantId(category,
        restaurantId);
  }

  public void validCategory(String category, String restaurantId) {
    categoryRepository.findByNameAndRestaurantId(category, restaurantId).orElseThrow(() -> {
      throw new CategoryException(Messages.RESTAURANT_OR_CATEGORY_NOT_FOUND);
    });
  }

  public Collection<CategoryResponse> getCategories(String restaurantId) {
    var dishes = categoryRepository.findAllByRestaurantId(restaurantId)
        .orElseThrow(() -> new CategoryException(
            Messages.RESTAURANT_NOT_FOUND));
    return dishes.stream().map(Category::toCategoryResponse).collect(Collectors.toList());
  }

  @Transactional
  public void deleteCategory(String category, String restaurantId) {
    if (existsCategory(category, restaurantId)) {
      dishService.deleteAllDishesByCategoryAndRestaurantId(category, restaurantId);
      categoryRepository.deleteByNameAndRestaurantId(category, restaurantId);
    } else {
      throw new CategoryException(Messages.CATEGORY_IS_INVALID);
    }
  }
}
