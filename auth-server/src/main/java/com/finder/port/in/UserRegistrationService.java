package com.finder.port.in;

import com.finder.domain.User;
import com.finder.exceprions.MailAlreadyExistsException;
import com.finder.exceprions.PasswordDontMatchException;
import com.finder.exceprions.UserAlreadyExistsException;
import com.finder.exceprions.UserNotFoundException;
import com.finder.port.models.request.UserRegistrationRequestModel;

public interface UserRegistrationService {

    //TODO: basic auth
    //TODO: oAuth2
    User register(UserRegistrationRequestModel user)
            throws UserAlreadyExistsException, MailAlreadyExistsException, PasswordDontMatchException;
    void activate(String token) throws UserNotFoundException;
}
