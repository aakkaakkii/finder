package com.finder.finder.adapter.persistence.mappers;

import com.finder.finder.adapter.persistence.entities.FoodEntity;
import com.finder.finder.adapter.persistence.entities.FoodTagEntity;
import com.finder.finder.domain.Food;
import com.finder.finder.domain.FoodTag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface FoodMapper {


    FoodTagMapper foodTagMapper = Mappers.getMapper(FoodTagMapper.class);

    @Mapping(target = "foodTags", expression = "java(toFoodTagsDomain(entity.getFoodTags()))")
    Food toDomain(FoodEntity entity);

    @Mapping(target = "foodTags", expression = "java(toFoodTagsEntity(domain.getFoodTags()))")
    FoodEntity toEntity(Food domain);

    default List<FoodTag> toFoodTagsDomain(List<FoodTagEntity> entities) {
        return entities == null ? null : entities.stream()
                .map(foodTagMapper::toDomain)
                .collect(Collectors.toList());
    }

    default List<FoodTagEntity> toFoodTagsEntity(List<FoodTag> domains) {
        return domains == null ? null : domains.stream()
                .map(foodTagMapper::toEntity)
                .collect(Collectors.toList());
    }
}
