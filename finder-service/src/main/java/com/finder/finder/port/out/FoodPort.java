package com.finder.finder.port.out;

import com.finder.finder.domain.Food;

import java.util.List;

public interface FoodPort {
    List<Food> load(int page, int limit);
    List<Food> loadActive(int page, int limit);
    List<Food> loadActiveFoodByTeg(Long tagId,int page, int limit);
    List<Food> getRandom5ActiveFoodExcludeByIds(List<Long> ids);
    Food getById(Long id);
    Food add(Food food);
    Food update(Food food);
    void delete(Long id);
}
