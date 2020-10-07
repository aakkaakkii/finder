package com.finder.port.out;

import com.finder.domain.Group;
import com.finder.domain.User;

import java.util.List;
import java.util.Set;

public interface GroupPort {
    List<Group> loadAll();
    Set<Group> loadByGroupIds(List<Long> ids);
    Group getById(Long id);
    Group add(Group group);
    Group update(Group group);
    void delete(Long id);
}
