package com.puc.campinas.rangubackendmenu.service;

import com.puc.campinas.rangubackendmenu.domain.Dish;
import com.puc.campinas.rangubackendmenu.repository.DishRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DishService {

  private DishRepository dishRepository;

  public Dish saveDish(Dish dish) {
    return dishRepository.save(dish);
  }

}
