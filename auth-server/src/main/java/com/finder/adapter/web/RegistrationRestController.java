package com.finder.adapter.web;

import com.finder.domain.User;
import com.finder.exceprions.MailAlreadyExistsException;
import com.finder.exceprions.PasswordDontMatchException;
import com.finder.exceprions.UserAlreadyExistsException;
import com.finder.exceprions.UserNotFoundException;
import com.finder.port.models.request.TokenRequestModel;
import com.finder.port.models.request.UserRegistrationRequestModel;
import com.finder.port.in.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/rest/v1/registration")
@RequiredArgsConstructor
public class RegistrationRestController {
    private final UserRegistrationService userRegistrationService;

    @PostMapping
    public User register(@RequestBody @Valid UserRegistrationRequestModel user)
            throws PasswordDontMatchException, UserAlreadyExistsException, MailAlreadyExistsException {
        return userRegistrationService.register(user);
    }

    @GetMapping("/activate/{token}")
    public void activate(@PathVariable String token) throws UserNotFoundException {
        userRegistrationService.activate(token);
    }

    @PostMapping("/activate")
    public void activate(@RequestBody TokenRequestModel token) throws UserNotFoundException {
        userRegistrationService.activate(token.token);
    }

}
