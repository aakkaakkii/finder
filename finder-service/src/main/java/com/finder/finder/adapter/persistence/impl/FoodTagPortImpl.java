package com.finder.finder.adapter.persistence.impl;

import com.finder.finder.adapter.persistence.mappers.FoodTagMapper;
import com.finder.finder.adapter.persistence.repository.FoodTagRepository;
import com.finder.finder.domain.FoodTag;
import com.finder.finder.port.out.FoodTagPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodTagPortImpl implements FoodTagPort {
    private final FoodTagRepository foodTagRepository;
    private final FoodTagMapper foodTagMapper;

    @Override
    public List<FoodTag> loadAll() {
        return foodTagRepository.findAll().stream()
                .map(foodTagMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public FoodTag getById(Long id) {
        return foodTagMapper.toDomain(foodTagRepository.getOne(id));
    }

    @Override
    public FoodTag add(FoodTag foodTag) {
        return foodTagMapper.toDomain(
                foodTagRepository.save(foodTagMapper.toEntity(foodTag))
        );
    }

    @Override
    public FoodTag update(FoodTag foodTag) {
        return foodTagMapper.toDomain(
                foodTagRepository.save(foodTagMapper.toEntity(foodTag))
        );
    }

    @Override
    public void delete(Long id) {
        foodTagRepository.deleteById(id);
    }
}
