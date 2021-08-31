package com.puc.campinas.rangubackendmenu.controller;

import com.puc.campinas.rangubackendmenu.domain.data.DishRequest;
import com.puc.campinas.rangubackendmenu.domain.data.DishResponse;
import com.puc.campinas.rangubackendmenu.service.DishService;
import java.util.Collection;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/api/rangu/v1/dishes")
@Slf4j
public class DishController {

  private DishService dishService;

  @PostMapping
  public ResponseEntity<DishResponse> createDish(
      @RequestBody @Valid DishRequest dishRequest) {
    //TODO verificar se restaurante existe
    var dish = dishService.saveDish(dishRequest.toDish());
    return ResponseEntity.status(HttpStatus.CREATED).body(dish.toDishResponse());
  }

  @GetMapping
  public ResponseEntity<Collection<DishResponse>> getDishes(
      @RequestHeader String restaurantId,
      @RequestParam(required = false) String category) {

    var dishes = StringUtils.isBlank(category) ?
        dishService.getDishes(restaurantId)
        : dishService.getDishesByCategory(restaurantId, category);
    return ResponseEntity.status(HttpStatus.OK).body(dishes);
  }

  @DeleteMapping()
  @ResponseStatus(HttpStatus.OK)
  public void deleteAllDishes(
      @RequestHeader String restaurantId) {
    dishService.deleteAllDishesByRestaurantId(restaurantId);
  }

  @DeleteMapping("/{dishId}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteDish(
      @PathVariable String dishId,
      @RequestHeader String restaurantId) {
    dishService.deleteDish(dishId, restaurantId);
  }

}
