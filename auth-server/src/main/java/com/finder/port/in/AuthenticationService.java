package com.finder.port.in;

import com.finder.exceprions.PasswordDontMatchException;
import com.finder.exceprions.UserIsBlockedException;
import com.finder.exceprions.UserIsNotActiveException;
import com.finder.exceprions.UserNotFoundException;
import com.finder.port.models.request.AuthenticationRequestModel;
import com.finder.port.models.response.AccessTokenResponse;

public interface AuthenticationService {
    AccessTokenResponse login(AuthenticationRequestModel auth)
            throws UserNotFoundException, PasswordDontMatchException, UserIsBlockedException, UserIsNotActiveException;
}
