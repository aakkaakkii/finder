package com.finder.adapter.web;

import com.finder.domain.Group;
import com.finder.port.in.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/v1/groups")
@RequiredArgsConstructor
public class GroupRestController {
    private final GroupService groupService;

    @GetMapping()
    public List<Group> loadAll(){
        return groupService.loadAll();
    }

    @GetMapping("/{id}")
    public Group getById(@PathVariable Long id){
        return groupService.getById(id);
    }

    @PostMapping
    public Group add(@RequestBody Group group) {
        return groupService.add(group);
    }

    @PutMapping("/{id}")
    public Group update(@RequestBody Group group, @PathVariable Long id) {
        return groupService.update(group);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        groupService.delete(id);
    }

}
