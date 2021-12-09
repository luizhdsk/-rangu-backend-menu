package com.puc.campinas.rangubackendmenu.repository;

import com.puc.campinas.rangubackendmenu.domain.RestaurantTable;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, String> {

  void deleteByRestaurantIdAndId(String restaurantId, String restaurantTableId);

  void deleteAllByRestaurantId(String restaurantId);

  List<RestaurantTable> getAllByRestaurantId(String restaurantId);

  Optional<RestaurantTable> getByClientTableId(String clientTableId);

  Optional<RestaurantTable> getRestaurantTableByNumberAndAndRestaurantId(String number, String restaurantId);

}
