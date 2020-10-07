package com.finder.adapter.persistence.impl;

import com.finder.adapter.persistence.meppers.UserMapper;
import com.finder.adapter.persistence.repository.UserRepository;
import com.finder.domain.User;
import com.finder.exceprions.UserNotFoundException;
import com.finder.port.out.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserPortImpl implements UserPort {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<User> loadUser(int page, int limit) {
        return userRepository.findAll(PageRequest.of(page, limit))
                .stream().map(userMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public User getByUsername(String username) throws UserNotFoundException {
        return userMapper.toDomain(userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username)));
    }

    @Override
    public User getByEmail(String email) throws UserNotFoundException{
        return userMapper.toDomain(userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email)));
    }

    @Override
    public User getById(Long id) throws UserNotFoundException {
        return userMapper.toDomain(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id)));
    }

    @Override
    public List<User> getUsersByGroupId(Long groupId, int page, int limit) {
        return userRepository.findUsersByGroupId(groupId, PageRequest.of(page, limit))
                .stream().map(userMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public User add(User user) {
        return userMapper.toDomain(
                userRepository.save(userMapper.toEntity(user))
        );
    }

    @Override
    public User update(User user) {
        return userMapper.toDomain(
                userRepository.save(userMapper.toEntity(user))
        );
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
