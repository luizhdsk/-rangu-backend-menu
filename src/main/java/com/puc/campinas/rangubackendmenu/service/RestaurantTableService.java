package com.puc.campinas.rangubackendmenu.service;

import com.puc.campinas.rangubackendmenu.config.Messages;
import com.puc.campinas.rangubackendmenu.config.exception.RestaurantTableException;
import com.puc.campinas.rangubackendmenu.domain.RestaurantTable;
import com.puc.campinas.rangubackendmenu.repository.RestaurantTableRepository;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RestaurantTableService {

  private RestaurantTableRepository restaurantTableRepository;

  public RestaurantTable saveRestaurantTable(RestaurantTable restaurantTable) {
    if (!existsRestaurantTable(restaurantTable)) {
      return restaurantTableRepository.save(restaurantTable);
    } else {
      throw new RestaurantTableException(Messages.RESTAURANT_TABLE_ALREADY_EXISTS);
    }
  }

  private boolean existsRestaurantTable(RestaurantTable restaurantTable) {
    return restaurantTableRepository
        .getRestaurantTableByNumberAndAndRestaurantId(restaurantTable.getNumber(),
            restaurantTable.getRestaurantId()).isPresent();
  }

  @Transactional
  public List<RestaurantTable> generateRestaurantTable(String restaurantId, Integer numberTables) {
    restaurantTableRepository.deleteAllByRestaurantId(restaurantId);
    var tables = generateTables(restaurantId, numberTables);
    return restaurantTableRepository.saveAll(tables);
  }

  private List<RestaurantTable> generateTables(String restaurantId, Integer numberTables) {
    var count = 1;
    List<RestaurantTable> tables = new ArrayList<>();
    while (numberTables + 1 > count) {
      tables.add(RestaurantTable.builder().restaurantId(restaurantId)
          .number(String.valueOf(count))
          .clientTableId(null).build());
      count++;
    }
    return tables;
  }

  public void deleteRestaurantTable(String restaurantId, String restaurantTableId) {
    restaurantTableRepository.deleteByRestaurantIdAndId(restaurantId, restaurantTableId);
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

  public List<RestaurantTable> getRestaurantTablesByRestaurantId(String restaurantId) {
    return restaurantTableRepository.getAllByRestaurantId(restaurantId);
  }
}
