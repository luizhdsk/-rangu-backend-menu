package com.puc.campinas.rangubackendmenu.controller;

import com.puc.campinas.rangubackendmenu.domain.data.CategoryRequest;
import com.puc.campinas.rangubackendmenu.domain.data.CategoryResponse;
import com.puc.campinas.rangubackendmenu.service.CategoryService;
import java.util.Collection;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    var category = categoryService.saveCategory(request.toCategory(restaurantId));
    return ResponseEntity.status(HttpStatus.CREATED).body(category.toCategoryResponse());
  }

  @GetMapping
  public ResponseEntity<Collection<CategoryResponse>> getCategories(
      @RequestHeader @Valid String restaurantId) {
    var categories = categoryService.getCategories(restaurantId);
    return ResponseEntity.status(HttpStatus.CREATED).body(categories);
  }

  @DeleteMapping
  public void deleteCategory(@RequestHeader @Valid String restaurantId,
      @RequestParam String category) {
    categoryService.deleteCategory(category, restaurantId);
  }

}
