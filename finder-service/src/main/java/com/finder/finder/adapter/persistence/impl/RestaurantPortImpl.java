package com.finder.finder.adapter.persistence.impl;

import com.finder.finder.adapter.persistence.repository.RestaurantImageRepository;
import com.finder.finder.adapter.persistence.repository.RestaurantRepository;
import com.finder.finder.domain.Restaurant;
import com.finder.finder.port.out.RestaurantPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantPortImpl implements RestaurantPort {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantImageRepository restaurantImageRepository;

    @Override
    public List<Restaurant> loadAll(int page, int limit) {
        return null;
    }

    @Override
    public Restaurant getById() {
        return null;
    }

    @Override
    public Restaurant add(Restaurant foodTag) {
        return null;
    }

    @Override
    public Restaurant update(Restaurant foodTag) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
