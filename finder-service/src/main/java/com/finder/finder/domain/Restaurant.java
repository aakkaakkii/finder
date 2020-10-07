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
public class Restaurant {
    private Long id;
    private String name;
    private String description;
    private List<String> imagePaths;
    private List<Food> foods;

}
