package com.finder.port.out;

import com.finder.domain.User;
import com.finder.exceprions.UserNotFoundException;

import java.util.List;

public interface UserPort {
    List<User> loadUser(int page, int limit);
    User getByUsername(String username) throws UserNotFoundException;
    User getByEmail(String email) throws UserNotFoundException;
    User getById(Long id) throws UserNotFoundException;
    List<User> getUsersByGroupId(Long groupId, int page, int limit);
    User add(User user);
    User update(User user);
    void delete(Long id);
}
