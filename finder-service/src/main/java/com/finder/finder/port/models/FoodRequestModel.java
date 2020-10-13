package com.finder.finder.port.models;

import com.finder.finder.domain.FoodTag;

import java.util.List;

public class FoodRequestModel {
    public String name;
    public String description;
    public Long restaurantId;
    public List<Long> foodTags;
    public double price;

}
