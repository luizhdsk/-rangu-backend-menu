package com.puc.campinas.rangubackendmenu.controller;

import com.puc.campinas.rangubackendmenu.domain.data.CategoryRequest;
import com.puc.campinas.rangubackendmenu.domain.data.CategoryResponse;
import com.puc.campinas.rangubackendmenu.service.CategoryService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/rangu/v1/category")
@Slf4j
public class CategoryController {

  private CategoryService categoryService;

  @PostMapping
  public ResponseEntity<CategoryResponse> createCategory(
      @RequestHeader @Valid String restaurantId,
      @RequestBody @Valid CategoryRequest request) {
    var category = categoryService.saveCategory(restaurantId, request.getName());
    return ResponseEntity.status(HttpStatus.CREATED).body(category.toCategoryResponse());
  }

}
