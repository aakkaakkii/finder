package com.finder.services;

import com.finder.domain.Group;
import com.finder.domain.User;
import com.finder.exceprions.MailAlreadyExistsException;
import com.finder.exceprions.UserAlreadyExistsException;
import com.finder.exceprions.UserNotFoundException;
import com.finder.port.out.GroupPort;
import com.finder.port.out.UserPort;
import com.finder.port.models.request.UserRequestModel;
import com.finder.port.in.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserPort userPort;
    private final GroupPort groupPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> load(int page, int limit) {
        return userPort.loadUser(page, limit).stream()
                .peek(user -> user.setPassword(""))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> loadByGroup(Long groupId, int page, int limit) {
        return userPort.getUsersByGroupId(groupId, page, limit);
    }

    @Override
    public User getByUsername(String username) {
        User user = null;
        try {
            user = userPort.getByUsername(username);
            user.setPassword("");
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getById(Long id) {
        User user = null;
        try {
            user = userPort.getById(id);
            user.setPassword("");
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User add(UserRequestModel user) throws UserAlreadyExistsException {
        validateUser(user);

        Set<Group> groups = groupPort.loadByGroupIds(user.groupsIds);

        User u = User.builder()
                .username(user.username)
                .email(user.email)
                .password(passwordEncoder.encode(user.password))
                .nickname(user.nickname)
                .blocked(user.blocked)
                .active(user.active)
                .permissions(user.permissions)
                .groups(groups)
                .build();
        return userPort.add(u);
    }

    @Override
    public User update(UserRequestModel user, Long id)
            throws UserNotFoundException, UserAlreadyExistsException {
        validateUser(user);

        User u = userPort.getById(id);
        Set<Group> groups = groupPort.loadByGroupIds(user.groupsIds);

        u.setUsername(user.username);
        u.setPassword(passwordEncoder.encode(user.password));
        u.setEmail(user.email);
        u.setNickname(user.nickname);
        u.setActive(user.active);
        u.setPermissions(user.permissions);
        u.setBlocked(user.blocked);
        u.setGroups(groups);

        return userPort.update(u);
    }

    @Override
    public void delete(Long id) {
        userPort.delete(id);
    }

    private void validateUser(UserRequestModel user) throws UserAlreadyExistsException {
        try {
            userPort.getByUsername(user.username);
            throw new UserAlreadyExistsException(user.username);
        } catch (UserNotFoundException ex) {
            System.out.println("asd");
        }
/*
        try {
            userPort.getByEmail(user.email);
            throw new MailAlreadyExistsException(user.email);
        } catch (UserNotFoundException ignored) {}
*/

    }
}
