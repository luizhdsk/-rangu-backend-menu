package com.puc.campinas.rangubackendmenu.service;

import com.puc.campinas.rangubackendmenu.config.Messages;
import com.puc.campinas.rangubackendmenu.config.exception.DishException;
import com.puc.campinas.rangubackendmenu.config.exception.RestaurantTableException;
import com.puc.campinas.rangubackendmenu.domain.Dish;
import com.puc.campinas.rangubackendmenu.domain.data.DishResponse;
import com.puc.campinas.rangubackendmenu.repository.DishRepository;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DishService {

  private DishRepository dishRepository;

  private CategoryService categoryService;

  public Dish saveDish(String restaurantId, Dish dish) {
    dish.setRestaurantId(restaurantId);
    validCategory(dish.getCategory(), dish.getRestaurantId());
    return dishRepository.save(dish);
  }

  private void validCategory(String category, String restaurantId) {
    categoryService.validCategory(category, restaurantId);
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

  @Transactional
  public void deleteDish(String dishId, String restaurantId) {
    dishRepository.deleteByIdAndRestaurantId(dishId, restaurantId);
  }

  public void deleteAllDishesByRestaurantId(String restaurantId) {
    dishRepository.deleteAllByRestaurantId(restaurantId);
  }

  public Collection<DishResponse> getDishesOrders(List<String> dishesId) {
    var dishes = dishRepository.findByIdIn(dishesId);
    if (dishes.size() != dishesId.size()) {
      throw new DishException(Messages.DISH_NOT_FOUND);
    }
    return dishes.stream().map(Dish::toDishResponse).collect(Collectors.toList());
  }

  public Dish updateDish(String dishId, String restaurantId, Dish dishUpdate) {
    var dish = dishRepository.findById(dishId).get();
    validCategory(dishUpdate.getCategory(), dish.getRestaurantId());
    if (!dish.getRestaurantId().equals(restaurantId)) {
      log.error("Invalid Restaurant 1={}, 2={}");
      throw new RestaurantTableException(Messages.DISH_NOT_FOUND);
    }
    dish.update(dishUpdate);
    return dishRepository.save(dish);

  }
}
