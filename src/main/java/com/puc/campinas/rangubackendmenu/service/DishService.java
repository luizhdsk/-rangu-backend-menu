package com.puc.campinas.rangubackendmenu.service;

import com.puc.campinas.rangubackendmenu.config.Messages;
import com.puc.campinas.rangubackendmenu.config.exception.DishException;
import com.puc.campinas.rangubackendmenu.domain.Dish;
import com.puc.campinas.rangubackendmenu.domain.data.DishResponse;
import com.puc.campinas.rangubackendmenu.repository.DishRepository;
import java.util.Collection;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DishService {

  private DishRepository dishRepository;

  public Dish saveDish(Dish dish) {
    return dishRepository.save(dish);
  }

  public Collection<DishResponse> getDishes(String restaurantId) {
    var dishes = dishRepository.findAllByRestaurantId(restaurantId)
        .orElseThrow(() -> new DishException(
            Messages.RESTAURANT_NOT_FOUND));
    return dishes.stream().map(Dish::toDishResponse).collect(Collectors.toList());
  }

  public Collection<DishResponse> getDishesByCategory(String restaurantId, String category) {
    var dishes = dishRepository.findAllByRestaurantIdAndCategory(restaurantId, category)
        .orElseThrow(() -> new DishException(Messages.RESTAURANT_OR_CATEGORY_NOT_FOUND));
    return dishes.stream().map(Dish::toDishResponse).collect(Collectors.toList());
  }

  public void deleteDish(String dishId, String restaurantId) {
    dishRepository.deleteByIdAndRestaurantId(dishId, restaurantId);
  }

  public void deleteAllDishesByRestaurantId(String restaurantId) {
    dishRepository.deleteAllByRestaurantId(restaurantId);
  }
}
