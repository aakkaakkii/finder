package com.finder.finder.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String imagePath;
    private List<FoodTag> foodTags;
}
