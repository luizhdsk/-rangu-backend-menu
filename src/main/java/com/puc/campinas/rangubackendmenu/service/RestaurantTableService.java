package com.puc.campinas.rangubackendmenu.service;

import com.puc.campinas.rangubackendmenu.domain.RestaurantTable;
import com.puc.campinas.rangubackendmenu.repository.RestaurantTableRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RestaurantTableService {

  private RestaurantTableRepository restaurantTableRepository;

  public RestaurantTable saveRestaurantTable(RestaurantTable restaurantTable) {
    return restaurantTableRepository.save(restaurantTable);
  }

  public void deleteRestaurantTable(String restaurantTableId) {
    restaurantTableRepository.deleteById(restaurantTableId);
  }
}
