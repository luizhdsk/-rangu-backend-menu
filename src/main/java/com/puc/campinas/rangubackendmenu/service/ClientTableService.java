package com.puc.campinas.rangubackendmenu.service;

import com.google.common.base.Strings;
import com.puc.campinas.rangubackendmenu.config.Messages;
import com.puc.campinas.rangubackendmenu.config.exception.ClientTableException;
import com.puc.campinas.rangubackendmenu.domain.ClientTable;
import com.puc.campinas.rangubackendmenu.domain.RestaurantTable;
import com.puc.campinas.rangubackendmenu.integration.users.UsersClient;
import com.puc.campinas.rangubackendmenu.repository.ClientTableRepository;
import java.time.Instant;
import java.util.Date;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientTableService {

  ClientTableRepository clientTableRepository;
  RestaurantTableService restaurantTableService;

  public ClientTable startTable(String clientId, String restaurantTableId) {
    var restaurantTable = getRestaurantTable(restaurantTableId);
    if (isEmpty(restaurantTable)) {
      var clientTable = createClientTable(clientId, restaurantTable);
      clientTable = clientTableRepository.save(clientTable);
      occupyRestaurantTable(restaurantTable, clientTable.getId());
      return clientTable;
    } else {
      var updateClientTable = clientTableRepository.findById(restaurantTable.getClientTableId())
          .get();
      updateClientTable.getTableMembers().add(clientId);
      return clientTableRepository.save(updateClientTable);
    }
  }

  private void occupyRestaurantTable(RestaurantTable restaurantTable, String clientTableId) {
    restaurantTableService.occupyRestaurantTable(restaurantTable, clientTableId);
  }

  private ClientTable createClientTable(String clientId, RestaurantTable restaurantTable) {
    return ClientTable.builder()
        .clientId(clientId)
        .number(restaurantTable.getNumber())
        .restaurantId(restaurantTable.getRestaurantId())
        .tableMembers(Set.of(clientId))
        .startDateTime(Instant.now())
        .build();
  }

  private boolean isEmpty(RestaurantTable restaurantTable) {
    return Strings.isNullOrEmpty(restaurantTable.getClientTableId());
  }

  private RestaurantTable getRestaurantTable(String tableId) {
    return restaurantTableService.getRestaurantTable(tableId);
  }


  public ClientTable getClientTable(String tableId) {
    return clientTableRepository.findById(tableId).orElseThrow(() -> new ClientTableException(
        Messages.CLIENT_TABLE_NOT_FOUND));
  }

  public ClientTable leaveTable(String tableId, String clientId) {
    var table = getClientTable(tableId);
    if(table.getClientId().equals(clientId)){
      var newOwner = table.getTableMembers().stream().findFirst().get();
      table.setClientId(newOwner);
    }
    table.getTableMembers().remove(clientId);
    clientTableRepository.save(table);
    return table;
  }
}
