package com.finder.finder.adapter.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private boolean isBlocked;
    private boolean isActive;
    @OneToMany
    private List<RestaurantImageEntity> imagePaths;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<FoodEntity> foods;
    private Long creatorId;
}
