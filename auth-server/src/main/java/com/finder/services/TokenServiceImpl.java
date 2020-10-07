package com.finder.services;

import com.finder.domain.User;
import com.finder.exceprions.TokenIsNotValidException;
import com.finder.exceprions.UserNotFoundException;
import com.finder.port.in.TokenService;
import com.finder.port.models.response.AccessTokenResponse;
import com.finder.port.out.UserPort;
import com.finder.utils.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final UserPort userPort;

    @Override
    public AccessTokenResponse refreshToken(String refreshToken)
            throws UserNotFoundException, TokenIsNotValidException {
        if (!JwtTokenProvider.validateToken(refreshToken)) {
            throw new TokenIsNotValidException();
        }

        User user = userPort.getByUsername(JwtTokenProvider.getUsername(refreshToken));
        return AccessTokenResponse.builder()
                .refreshToken(user.createRefreshToken())
                .accessToken(user.createAccessToken())
                .build();
    }
}
