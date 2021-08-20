package com.puc.campinas.rangubackendmenu.controller;

import com.puc.campinas.rangubackendmenu.domain.data.RestaurantTableRequest;
import com.puc.campinas.rangubackendmenu.domain.data.RestaurantTableResponse;
import com.puc.campinas.rangubackendmenu.service.RestaurantTableService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/rangu/v1/tables")
@Slf4j
public class RestaurantTableController {

  private RestaurantTableService restaurantTableService;

  @PostMapping
  public ResponseEntity<RestaurantTableResponse> createTable(
      @RequestBody @Valid RestaurantTableRequest restaurantTableRequest) {
    var restaurantTable = restaurantTableService
        .saveRestaurantTable(restaurantTableRequest.toRestaurantTable());
    return ResponseEntity.status(HttpStatus.CREATED).body(restaurantTable.toRestaurantTableResponse());
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.OK)
  public void deleteTable(
      @RequestBody @Valid String restaurantTableId) {
    restaurantTableService.deleteRestaurantTable(restaurantTableId);
  }

}
