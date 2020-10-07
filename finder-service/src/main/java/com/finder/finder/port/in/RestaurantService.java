package com.finder.finder.port.in;

import com.finder.finder.domain.Food;
import com.finder.finder.domain.FoodTag;

import java.util.List;

public interface RestaurantService {
    List<Food> loadAll(int page, int limit);
    List<Food> getFood();
    FoodTag getById(Long id);
    FoodTag add(FoodTag foodTag);
    FoodTag update(FoodTag foodTag);
    void delete(Long id);
}
