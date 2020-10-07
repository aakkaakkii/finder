package com.finder.adapter.web;

import com.finder.domain.User;
import com.finder.exceprions.UserAlreadyExistsException;
import com.finder.exceprions.UserNotFoundException;
import com.finder.port.models.request.UserRequestModel;
import com.finder.port.in.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/v1/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @GetMapping
    public List<User> load(@RequestParam int start, @RequestParam Integer limit){
        return userService.loadUser(start, limit);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping("/username/{username}")
    public User getByUsername(@PathVariable String username) {
        return userService.getByUsername(username);
    }

    @GetMapping("/group/{id}")
    public List<User> loadByGroup(@PathVariable Long id, @RequestParam int start, @RequestParam Integer limit){
        return userService.loadByGroup(id, start, limit);
    }

    @PostMapping
    public User add(@RequestBody UserRequestModel user) throws UserAlreadyExistsException {
        return userService.add(user);
    }

    @PutMapping("/{id}")
    public User update(@RequestBody UserRequestModel user, @PathVariable Long id)
            throws UserAlreadyExistsException, UserNotFoundException {
        return userService.update(user, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

}
