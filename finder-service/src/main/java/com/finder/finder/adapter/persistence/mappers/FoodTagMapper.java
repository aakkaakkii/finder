package com.finder.finder.adapter.persistence.mappers;

import com.finder.finder.adapter.persistence.entities.FoodTagEntity;
import com.finder.finder.domain.FoodTag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodTagMapper {
    FoodTag toDomain(FoodTagEntity entity);
    FoodTagEntity toEntity(FoodTag domain);

}
