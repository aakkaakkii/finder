package com.finder.services;

import com.finder.domain.User;
import com.finder.exceprions.PasswordDontMatchException;
import com.finder.exceprions.UserIsBlockedException;
import com.finder.exceprions.UserIsNotActiveException;
import com.finder.exceprions.UserNotFoundException;
import com.finder.port.out.UserPort;
import com.finder.port.models.request.AuthenticationRequestModel;
import com.finder.port.models.response.AccessTokenResponse;
import com.finder.port.in.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserPort userPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AccessTokenResponse login(AuthenticationRequestModel auth)
            throws UserNotFoundException, PasswordDontMatchException, UserIsNotActiveException, UserIsBlockedException {
        User user = userPort.getByUsername(auth.username);

        if (!passwordEncoder.matches(auth.password, user.getPassword())) {
            throw new PasswordDontMatchException();
        }
        if (!user.isActive()) {
            throw new UserIsNotActiveException(auth.username);
        }
        if (user.isBlocked()) {
            throw new UserIsBlockedException(auth.username);
        }

        return AccessTokenResponse.builder()
                .accessToken(user.createAccessToken())
                .refreshToken(user.createRefreshToken())
                .build();
    }
}
