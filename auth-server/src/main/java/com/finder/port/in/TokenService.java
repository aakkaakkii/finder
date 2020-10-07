package com.finder.port.in;

import com.finder.exceprions.TokenIsNotValidException;
import com.finder.exceprions.UserNotFoundException;
import com.finder.port.models.response.AccessTokenResponse;

public interface TokenService {

    //TODO: refresh token
    AccessTokenResponse refreshToken(String tokenRequestModel)
            throws UserNotFoundException, TokenIsNotValidException;
}
