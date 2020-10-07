package com.finder.finder.port.in;

import com.finder.finder.domain.Food;
import com.finder.finder.domain.FoodTag;

import java.util.List;

public interface FoodService {
    List<Food> loadAll(int page, int limit);
    List<Food> getRandom5FoodExcludeByIds(List<Long> ids);
    List<Food> loadByTag(FoodTag tag);
    Food getById(Long id);
}
