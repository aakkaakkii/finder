package com.finder.port.in;

import com.finder.domain.Group;

import java.util.List;

public interface GroupService {
    List<Group> loadAll();
    Group getById(Long id);
    Group add(Group group);
    Group update(Group group, Long id);
    void delete(Long id);
}
