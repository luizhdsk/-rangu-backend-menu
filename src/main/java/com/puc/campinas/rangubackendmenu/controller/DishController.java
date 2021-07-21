package com.puc.campinas.rangubackendmenu.controller;

import com.puc.campinas.rangubackendmenu.domain.data.DishRequest;
import com.puc.campinas.rangubackendmenu.integration.UserClient;
import com.puc.campinas.rangubackendmenu.service.DishService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/rangu/v1/dishes")
@Slf4j
public class DishController {

  private DishService dishService;
  private UserClient userClient;

  @PostMapping
  @PreAuthorize("hasRole('RESTAURANT')")
  public ResponseEntity<?> createDish(@RequestBody @Valid DishRequest dishRequest,
      @RequestHeader("Authorization") String token) {
    var dish = dishService.saveDish(dishRequest.toDish());
    return ResponseEntity.status(HttpStatus.CREATED).body(dish.toDishResponse());
  }

}
