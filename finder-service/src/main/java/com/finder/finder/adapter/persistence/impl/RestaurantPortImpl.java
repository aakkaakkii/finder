package com.finder.finder.adapter.persistence.impl;

import com.finder.finder.adapter.persistence.entities.RestaurantEntity;
import com.finder.finder.adapter.persistence.entities.RestaurantImageEntity;
import com.finder.finder.adapter.persistence.mappers.RestaurantMapper;
import com.finder.finder.adapter.persistence.repository.RestaurantImageRepository;
import com.finder.finder.adapter.persistence.repository.RestaurantRepository;
import com.finder.finder.domain.Restaurant;
import com.finder.finder.port.out.RestaurantPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantPortImpl implements RestaurantPort {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantImageRepository restaurantImageRepository;
    private final RestaurantMapper restaurantMapper;

    @Override
    public List<Restaurant> loadAll(int page, int limit) {
        return restaurantRepository.findAll(PageRequest.of(page, limit))
                .stream().map(restaurantMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Restaurant getByFoodId(Long id) {
        return restaurantMapper.toDomain(restaurantRepository.getByFoodId(id));
    }

    @Override
    public Restaurant getById(Long id) {
        return restaurantMapper.toDomain(restaurantRepository.getOne(id));
    }

    @Override
    public Restaurant add(Restaurant restaurant) {
        RestaurantEntity restaurantEntity = restaurantMapper.toEntity(restaurant);
        List<RestaurantImageEntity> images = restaurant.getImagePaths().stream()
                .map(s -> RestaurantImageEntity.builder()
                        .imagePath(s).build()
                )
                .collect(Collectors.toList());

        List<RestaurantImageEntity> savedImages = restaurantImageRepository.saveAll(images);
        restaurantEntity.setImagePaths(savedImages);

        return restaurantMapper.toDomain(restaurantRepository.save(restaurantEntity));
    }

    @Override
    public Restaurant update(Restaurant restaurant) {
        return null;
    }

    @Override
    public void delete(Long id) {
        restaurantRepository.deleteById(id);
    }
}
