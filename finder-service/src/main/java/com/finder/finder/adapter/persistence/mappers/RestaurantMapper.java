package com.finder.finder.adapter.persistence.mappers;

import com.finder.finder.adapter.persistence.entities.FoodEntity;
import com.finder.finder.adapter.persistence.entities.RestaurantEntity;
import com.finder.finder.adapter.persistence.entities.RestaurantImageEntity;
import com.finder.finder.domain.Food;
import com.finder.finder.domain.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    FoodMapper foodMapper = Mappers.getMapper(FoodMapper.class);

    @Mapping(target = "foods", expression = "java(toFoodsDomain(entity.getFoods()))")
    @Mapping(target = "imagePaths", expression = "java(toImagePathsDomain(entity.getImagePaths()))")
    Restaurant toDomain(RestaurantEntity entity);

    @Mapping(target = "foods", expression = "java(toFoodsEntity(domain.getFoods()))")
//    @Mapping(target = "imagePaths", expression = "java(toImagePathsEntity(domain.getFoods()))")
    @Mapping(target = "imagePaths", ignore = true)
    RestaurantEntity toEntity(Restaurant domain);

    default List<Food> toFoodsDomain(List<FoodEntity> entities) {
        return entities == null ? null : entities.stream()
                .map(foodMapper::toDomain)
                .collect(Collectors.toList());
    }

    default List<FoodEntity> toFoodsEntity(List<Food> domains) {
        return domains == null ? null : domains.stream()
                .map(foodMapper::toEntity)
                .collect(Collectors.toList());
    }

    default List<String> toImagePathsDomain(List<RestaurantImageEntity> entities) {
        return entities == null ? null : entities.stream()
                .map(RestaurantImageEntity::getImagePath)
                .collect(Collectors.toList());
    }

//    default List<RestaurantImageEntity>
}
