package com.finder.finder.port.in;

import com.finder.finder.domain.Food;
import com.finder.finder.port.models.FoodRequestModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FoodService {
    List<Food> load(int page, int limit);

    List<Food> loadActive(int page, int limit);

    List<Food> getRandom5ActiveFoodExcludeByIds(List<Long> ids);

    List<Food> loadActiveByTag(Long tagId, int page, int limit);

    Food getById(Long id);

    Food add(FoodRequestModel food, MultipartFile file, Long restaurantId, Long initiatorId);

    Food update(Long id, FoodRequestModel food, MultipartFile file, Long restaurantId, Long initiatorId);

    void delete(Long id, Long initiatorId);

    Food privilegeUpdate(Long id, FoodRequestModel food, MultipartFile file);

    void privilegeDelete(Long id);
}
