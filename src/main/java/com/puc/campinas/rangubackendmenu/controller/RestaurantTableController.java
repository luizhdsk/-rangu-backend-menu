package com.puc.campinas.rangubackendmenu.controller;

import com.puc.campinas.rangubackendmenu.domain.RestaurantTable;
import com.puc.campinas.rangubackendmenu.domain.data.RestaurantTableRequest;
import com.puc.campinas.rangubackendmenu.domain.data.RestaurantTableResponse;
import com.puc.campinas.rangubackendmenu.service.RestaurantTableService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/rangu/v1/restaurantTables")
@Slf4j
public class RestaurantTableController {

  private RestaurantTableService restaurantTableService;

  @PostMapping
  public ResponseEntity<RestaurantTableResponse> createTable(
      @RequestHeader @Valid String restaurantId,
      @RequestBody @Valid RestaurantTableRequest request) {
    var restaurantTable = restaurantTableService
        .saveRestaurantTable(RestaurantTable.builder()
            .restaurantId(restaurantId)
            .number(request.getNumber())
            .clientTableId(null).build());
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(restaurantTable.toRestaurantTableResponse());
  }

  @DeleteMapping("/{restaurantTableId}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteTable(@PathVariable String restaurantTableId) {
    restaurantTableService.deleteRestaurantTable(restaurantTableId);
  }

}
