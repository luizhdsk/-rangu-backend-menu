package com.puc.campinas.rangubackendmenu.repository;

import com.puc.campinas.rangubackendmenu.domain.Category;
import com.puc.campinas.rangubackendmenu.domain.Dish;
import java.util.Collection;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

  Optional<Category> findByNameAndRestaurantId(String name, String restaurantId);

  Boolean existsByNameAndRestaurantId(String name, String restaurantId);

  Optional<Collection<Category>> findAllByRestaurantId(String restaurantId);

}
