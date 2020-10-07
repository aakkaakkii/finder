package com.finder.adapter.web;

import com.finder.exceprions.PasswordDontMatchException;
import com.finder.exceprions.UserIsBlockedException;
import com.finder.exceprions.UserIsNotActiveException;
import com.finder.exceprions.UserNotFoundException;
import com.finder.port.models.request.AuthenticationRequestModel;
import com.finder.port.models.response.AccessTokenResponse;
import com.finder.port.in.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/v1/auth")
@RequiredArgsConstructor
public class AuthenticationRestController {
    private final AuthenticationService authenticationService;

    @PostMapping
    public AccessTokenResponse login(@RequestBody AuthenticationRequestModel auth)
            throws UserNotFoundException, UserIsBlockedException, UserIsNotActiveException, PasswordDontMatchException {
        return authenticationService.login(auth);
    }
}
