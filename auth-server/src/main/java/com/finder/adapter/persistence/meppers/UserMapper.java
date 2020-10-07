package com.finder.adapter.persistence.meppers;

import com.finder.adapter.persistence.entities.GroupEntity;
import com.finder.adapter.persistence.entities.UserEntity;
import com.finder.domain.Group;
import com.finder.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    GroupMapper groupMapper = Mappers.getMapper(GroupMapper.class);

    @Mapping(target = "groups", expression = "java(toGroupDomain(entity.getGroups()))")
    User toDomain(UserEntity entity);
    @Mapping(target = "groups", expression = "java(toGroupEntity(domain.getGroups()))")
    UserEntity toEntity(User domain);

    default Set<Group> toGroupDomain(Set<GroupEntity> entities) {
        return entities == null ? null : entities.stream()
                .map(groupMapper::toDomain)
                .collect(Collectors.toSet());
    }

    default Set<GroupEntity> toGroupEntity(Set<Group> domains) {
        return domains == null ? null : domains.stream()
                .map(groupMapper::toEntity)
                .collect(Collectors.toSet());
    }
}
