package com.finder.adapter.persistence.meppers;

import com.finder.adapter.persistence.entities.GroupEntity;
import com.finder.domain.Group;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    Group toDomain(GroupEntity entity);
    GroupEntity toEntity(Group group);
}
