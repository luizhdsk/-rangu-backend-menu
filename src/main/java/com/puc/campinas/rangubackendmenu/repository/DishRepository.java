package com.puc.campinas.rangubackendmenu.repository;

import com.puc.campinas.rangubackendmenu.domain.Dish;
import java.util.Collection;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, String> {

  Optional<Collection<Dish>> findAllByRestaurantId(String restaurantId);

  Optional<Collection<Dish>> findAllByRestaurantIdAndCategory(String restaurantId, String category);

  void deleteByIdAndRestaurantId(String dishId, String restaurantId);

  void deleteAllByRestaurantId(String restaurantId);

}
