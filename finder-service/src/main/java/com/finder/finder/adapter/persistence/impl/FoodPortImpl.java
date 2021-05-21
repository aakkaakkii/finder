package com.finder.finder.adapter.persistence.impl;

import com.finder.finder.adapter.persistence.entities.FoodEntity;
import com.finder.finder.adapter.persistence.mappers.FoodMapper;
import com.finder.finder.adapter.persistence.repository.FoodRepository;
import com.finder.finder.adapter.persistence.repository.search.FoodSearchRepository;
import com.finder.finder.domain.Food;
import com.finder.finder.port.out.FoodPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodPortImpl implements FoodPort {
    private final FoodRepository foodRepository;
    private final FoodMapper foodMapper;
    private final FoodSearchRepository foodSearchRepository;

    @Override
    public List<Food> load(int page, int limit) {
        return foodRepository.findAll(PageRequest.of(page, limit))
                .stream().map(foodMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Food> loadActive(int page, int limit) {
        return foodRepository.loadActive(PageRequest.of(page, limit))
                .stream().map(foodMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Food> loadActiveFoodByTeg(Long tagId, int page, int limit) {
        return foodRepository.loadActiveFoodByTeg(tagId, PageRequest.of(page, limit))
                .stream().map(foodMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Food> getRandom5ActiveFoodExcludeByIds(List<Long> ids) {
        int totalSize = (int) foodRepository.count();
        int bound = new Random().nextInt(totalSize);

        return foodRepository.findActiveExcludeByIds(ids, PageRequest.of(bound, 5))
                .stream().map(foodMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Food getById(Long id) {
        return foodMapper.toDomain(foodRepository.getOne(id));
    }

    @Override
    public Food add(Food food) {
        FoodEntity foodEntity = foodMapper.toEntity(food);
        foodSearchRepository.save(foodEntity);

        return foodMapper.toDomain(
                foodRepository.save(foodEntity)
        );
    }

    @Override
    public Food update(Food food) {
        FoodEntity foodEntity = foodMapper.toEntity(food);
        foodSearchRepository.save(foodEntity);

        return foodMapper.toDomain(
                foodRepository.save(foodEntity)
        );
    }

    @Override
    public void delete(Long id) {
        foodSearchRepository.deleteById(id);
        foodRepository.deleteById(id);
    }
}
