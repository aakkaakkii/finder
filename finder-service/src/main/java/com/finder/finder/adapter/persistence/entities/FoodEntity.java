package com.finder.finder.adapter.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private double price;
    private String imagePath;
    @ManyToMany
    @JoinTable(name = "food_foodTag",
            joinColumns = { @JoinColumn(name = "fk_food") },
            inverseJoinColumns = { @JoinColumn(name = "fk_foodTag") })
    private List<FoodTagEntity> foodTags =  new ArrayList<>();

}
