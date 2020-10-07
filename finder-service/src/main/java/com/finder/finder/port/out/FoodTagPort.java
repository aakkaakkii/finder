package com.finder.finder.port.out;

import com.finder.finder.domain.Food;
import com.finder.finder.domain.FoodTag;

import java.util.List;

public interface FoodTagPort {
    List<FoodTag> loadAll();
    FoodTag getById(Long id);
    FoodTag add(FoodTag foodTag);
    FoodTag update(FoodTag foodTag);
    void delete(Long id);
}
