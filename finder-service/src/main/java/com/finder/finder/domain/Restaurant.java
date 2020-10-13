package com.finder.finder.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    private Long id;
    private String name;
    private String description;
    private List<String> imagePaths = new ArrayList<>();
    private List<Food> foods = new ArrayList<>();
    private boolean isBlocked = false;
    private boolean isActive = false;
    private Long creatorId;

    public void addFood(Food food) {
        if(foods == null) {
            foods = new ArrayList<>();
        }

        foods.add(food);
    }
}
