package com.finder.adapter.persistence.impl;

import com.finder.adapter.persistence.meppers.GroupMapper;
import com.finder.adapter.persistence.meppers.UserMapper;
import com.finder.adapter.persistence.repository.GroupRepository;
import com.finder.domain.Group;
import com.finder.domain.User;
import com.finder.port.out.GroupPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GroupPortImpl implements GroupPort {
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final UserMapper userMapper;

    @Override
    public List<Group> loadAll() {
        return groupRepository.findAll()
                .stream().map(groupMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Set<Group> loadByGroupIds(List<Long> ids) {
        return groupRepository.findAllById(ids)
                .stream().map(groupMapper::toDomain)
                .collect(Collectors.toSet());
    }

    @Override
    public Group getById(Long id) {
        return groupMapper.toDomain(groupRepository.getOne(id));
    }

    @Override
    public Group add(Group group) {
        return groupMapper.toDomain(
                groupRepository.save(groupMapper.toEntity(group)));
    }

    @Override
    public Group update(Group group) {
        return groupMapper.toDomain(
                groupRepository.save(groupMapper.toEntity((group))));
    }

    @Override
    public void delete(Long id) {
        groupRepository.deleteById(id);
    }
}
