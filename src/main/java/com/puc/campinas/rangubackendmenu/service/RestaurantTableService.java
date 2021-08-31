package com.puc.campinas.rangubackendmenu.service;

import com.puc.campinas.rangubackendmenu.config.Messages;
import com.puc.campinas.rangubackendmenu.config.exception.RestaurantTableException;
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

  public RestaurantTable getRestaurantTable(String restaurantTableId) {
    return restaurantTableRepository.findById(restaurantTableId)
        .orElseThrow(() -> new RestaurantTableException(
            Messages.RESTAURANT_TABLE_NOT_FOUND));
  }

  public void occupyRestaurantTable(RestaurantTable restaurantTable, String clientTableId) {
    restaurantTable.setClientTableId(clientTableId);
    saveRestaurantTable(restaurantTable);
  }
}
