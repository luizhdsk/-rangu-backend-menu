package com.puc.campinas.rangubackendmenu.repository;

import com.puc.campinas.rangubackendmenu.domain.Dish;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, String> {

  Collection<Dish> findAllByRestaurantId(String restaurantId);

  Collection<Dish> findAllByRestaurantIdAndCategory(String restaurantId, String category);

  void deleteByIdAndRestaurantId(String dishId, String restaurantId);

  void deleteAllByRestaurantId(String restaurantId);

}
