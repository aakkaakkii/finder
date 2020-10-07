package com.finder.port.in;

import com.finder.domain.Group;
import com.finder.domain.User;
import com.finder.exceprions.UserAlreadyExistsException;
import com.finder.exceprions.UserNotFoundException;
import com.finder.port.models.request.UserRequestModel;

import java.util.List;

public interface UserService {
    List<User> loadUser(int page, int limit);
    List<User> loadByGroup(Long groupId, int page, int limit);
    User getByUsername(String username);
    User getById(Long id);
    User add(UserRequestModel user) throws UserAlreadyExistsException;
    User update(UserRequestModel user, Long id) throws UserAlreadyExistsException, UserNotFoundException;
    void delete(Long id);
}
