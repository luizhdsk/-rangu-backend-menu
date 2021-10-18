package com.puc.campinas.rangubackendmenu.controller;

import com.puc.campinas.rangubackendmenu.domain.RestaurantTable;
import com.puc.campinas.rangubackendmenu.domain.data.RestaurantTableRequest;
import com.puc.campinas.rangubackendmenu.domain.data.RestaurantTableResponse;
import com.puc.campinas.rangubackendmenu.service.RestaurantTableService;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
      @RequestBody @Valid RestaurantTableRequest restaurantTableRequest) {
    var restaurantTable = restaurantTableService
        .saveRestaurantTable(restaurantTableRequest.toRestaurantTable(restaurantId));
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(restaurantTable.toRestaurantTableResponse());
  }

  @PostMapping("/generate")
  public ResponseEntity<?> generateTables(
      @RequestHeader @Valid String restaurantId,
      @RequestParam Integer numberTables) {
    var restaurantTables = restaurantTableService
        .generateRestaurantTable(restaurantId, numberTables);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(restaurantTables.stream().map(RestaurantTable::toRestaurantTableResponse).collect(
            Collectors.toList()));
  }

  @GetMapping("/{restaurantTableId}")
  public ResponseEntity<?> getRestaurantTableById(@PathVariable String restaurantTableId) {
    var restaurantTable = restaurantTableService.getRestaurantTable(restaurantTableId);
    return ResponseEntity.ok(restaurantTable.toRestaurantTableResponse());
  }

  @GetMapping
  public ResponseEntity<?> getAllRestaurantTable(@RequestHeader String restaurantId) {
    var restaurantTables = restaurantTableService.getRestaurantTablesByRestaurantId(restaurantId);
    return ResponseEntity.ok(restaurantTables.stream().map(RestaurantTable::toRestaurantTableResponse).collect(
        Collectors.toList()));
  }

  @DeleteMapping("/{restaurantTableId}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteTable(@RequestHeader @Valid String restaurantId,
      @PathVariable String restaurantTableId) {
    restaurantTableService.deleteRestaurantTable(restaurantId, restaurantTableId);
  }

}
