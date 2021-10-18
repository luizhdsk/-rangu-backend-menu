package com.puc.campinas.rangubackendmenu.repository;

import com.puc.campinas.rangubackendmenu.domain.Dish;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, String> {

  Optional<Collection<Dish>> findAllByRestaurantId(String restaurantId);

  Optional<Collection<Dish>> findAllByRestaurantIdAndCategory(String restaurantId, String category);

  Collection<Dish> findByIdIn(List<String> dishesIds);

  void deleteByIdAndRestaurantId(String dishId, String restaurantId);

  void deleteAllByRestaurantId(String restaurantId);

}
