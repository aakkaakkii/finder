package com.finder.services;

import com.finder.domain.Group;
import com.finder.port.out.GroupPort;
import com.finder.port.in.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupPort groupPort;

    @Override
    public List<Group> loadAll() {
        return groupPort.loadAll();
    }

    @Override
    public Group getById(Long id) {
        return groupPort.getById(id);
    }

    @Override
    public Group add(Group group) {
        return groupPort.add(group);
    }

    @Override
    public Group update(Group group) {
        return groupPort.update(group);
    }

    @Override
    public void delete(Long id) {
        groupPort.delete(id);
    }
}
