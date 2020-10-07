package com.finder.finder.port.out;

import com.finder.finder.domain.Food;

import java.util.List;

public interface FoodPort {
    List<Food> loadAll(int page, int limit);
    List<Food> loadFoodByTeg(Long tagId,int page, int limit);
    List<Food> getRandom5FoodExcludeByIds(List<Long> ids);
    Food getById(Long id);
    Food add(Food food);
    Food update(Food food);
    void delete(Long id);
}
