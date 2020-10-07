package com.finder.finder.adapter.persistence.impl;

import com.finder.finder.adapter.persistence.mappers.FoodMapper;
import com.finder.finder.adapter.persistence.repository.FoodRepository;
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

    @Override
    public List<Food> loadAll(int page, int limit) {
        return foodRepository.findAll(PageRequest.of(page, limit))
                .stream().map(foodMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Food> loadFoodByTeg(Long tagId, int page, int limit) {
        return foodRepository.loadFoodByTeg(tagId, PageRequest.of(page, limit))
                .stream().map(foodMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Food> getRandom5FoodExcludeByIds(List<Long> ids) {
        int totalSize = (int) foodRepository.count();
        int bound = new Random().nextInt(totalSize);

        return foodRepository.findAllExcludeByIds(ids, PageRequest.of(bound, 5))
                .stream().map(foodMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Food getById(Long id) {
        return foodMapper.toDomain(foodRepository.getOne(id));
    }

    @Override
    public Food add(Food food) {
        return foodMapper.toDomain(
                foodRepository.save(foodMapper.toEntity(food))
        );
    }

    @Override
    public Food update(Food food) {
        return foodMapper.toDomain(
                foodRepository.save(foodMapper.toEntity(food))
        );
    }

    @Override
    public void delete(Long id) {
        foodRepository.deleteById(id);
    }
}
