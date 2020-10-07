package com.finder.finder.port.in;

import com.finder.finder.domain.FoodTag;

import java.util.List;

public interface FoodTagService {
    List<FoodTag> getAll();
    FoodTag getById(Long id);
    FoodTag add(FoodTag foodTag);
    FoodTag update(FoodTag foodTag);
    void delete(Long id);
}
