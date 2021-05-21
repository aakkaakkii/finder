package com.finder.finder.port.out;

import com.finder.finder.domain.Food;

import java.util.List;

public interface FoodSearchPort {
    List<Food> search(String query);
}
