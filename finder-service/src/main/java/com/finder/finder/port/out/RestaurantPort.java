package com.finder.finder.port.out;

import com.finder.finder.domain.Restaurant;

import java.util.List;

public interface RestaurantPort {
    List<Restaurant> loadAll(int page, int limit);
    List<Restaurant> loadActive(int page, int limit);
    Restaurant getByFoodId(Long id);
    Restaurant getById(Long id);
    Restaurant add(Restaurant restaurant);
    Restaurant update(Restaurant restaurant);
    void delete(Long id);
}
