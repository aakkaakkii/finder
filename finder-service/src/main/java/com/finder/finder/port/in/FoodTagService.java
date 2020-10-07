package com.finder.finder.port.in;

import com.finder.finder.domain.FoodTag;
import com.finder.finder.port.models.FoodTagRequest;

import java.util.List;

public interface FoodTagService {
    List<FoodTag> load();
    FoodTag getById(Long id);
    FoodTag add(FoodTagRequest foodTag);
    FoodTag update(FoodTagRequest foodTag, Long id);
    void delete(Long id);
}
