package com.finder.services;

import com.finder.domain.Permission;
import com.finder.domain.User;
import com.finder.exceprions.MailAlreadyExistsException;
import com.finder.exceprions.PasswordDontMatchException;
import com.finder.exceprions.UserAlreadyExistsException;
import com.finder.exceprions.UserNotFoundException;
import com.finder.port.out.MailSenderPort;
import com.finder.port.out.UserPort;
import com.finder.port.models.request.UserRegistrationRequestModel;
import com.finder.port.in.UserRegistrationService;
import com.finder.utils.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserRegistrationServiceImpl implements UserRegistrationService {
    private final UserPort userPort;
    private final PasswordEncoder passwordEncoder;
    private final MailSenderPort mailSenderPort;

    @Override
    public User register(UserRegistrationRequestModel user)
            throws PasswordDontMatchException, UserAlreadyExistsException, MailAlreadyExistsException {
        validateUser(user);

        User newUser = User.builder()
                .username(user.getUsername())
                .nickname(user.getNickname())
                .password(passwordEncoder.encode(user.getPassword()))
                .blocked(false)
                .permissions(Collections.singleton(Permission.GENERIC_USER))
                .build();

        newUser = userPort.add(newUser);

        mailSenderPort.sendActivationMail(newUser.createToken(), newUser.getEmail());

        newUser.setPassword("");
        return newUser;
    }

    @Override
    public void activate(String token) throws UserNotFoundException {
        User user = userPort.getByUsername(JwtTokenProvider.getUsername(token));

        if (user != null) {
            user.setActive(true);
            userPort.update(user);
        }
    }

    private void validateUser(UserRegistrationRequestModel user)
            throws PasswordDontMatchException, UserAlreadyExistsException, MailAlreadyExistsException {
        try {
            userPort.getByUsername(user.getUsername());
            throw new UserAlreadyExistsException(user.getUsername());
        } catch (UserNotFoundException ignored) {}
        try {
            userPort.getByEmail(user.getMail());
            throw new MailAlreadyExistsException(user.getMail());
        } catch (UserNotFoundException ignored) {}
        if (!user.getPassword().equals(user.getRepeatPassword())) {
            throw new PasswordDontMatchException();
        }
    }
}
