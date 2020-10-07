package com.finder.finder.services;

import com.finder.finder.domain.Food;
import com.finder.finder.domain.FoodTag;
import com.finder.finder.port.in.FoodService;
import com.finder.finder.port.out.FoodPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {
    private final FoodPort foodPort;

    @Override
    public List<Food> loadAll(int page, int limit) {
        return foodPort.loadAll(page, limit);
    }

    @Override
    public List<Food> getRandom5FoodExcludeByIds(List<Long> ids) {
        return foodPort.getRandom5FoodExcludeByIds(ids);
    }

    @Override
    public List<Food> loadByTag(FoodTag tag) {
        return null;
    }

    @Override
    public Food getById(Long id) {
        return foodPort.getById(id);
    }

    @Override
    public Food add(Food food, Long restaurantId, Long initiatorId) {
        return null;
    }

    @Override
    public Food update(Food food, Long restaurantId, Long initiatorId) {
        return null;
    }
}
