package com.finder.adapter.web;

import com.finder.exceprions.PasswordDontMatchException;
import com.finder.exceprions.UserNotFoundException;
import com.finder.port.in.ChangePasswordService;
import com.finder.port.in.PasswordResetService;
import com.finder.port.models.request.ChangePasswordRequestModel;
import com.finder.port.models.request.PasswordRequestModel;
import com.finder.port.models.request.ResetPasswordRequestModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/v1/edit")
@RequiredArgsConstructor
public class UserEditRestController {
    private final ChangePasswordService changePasswordService;
    private final PasswordResetService passwordResetService;

    @PostMapping("/password/change")
    @PreAuthorize("hasAuthority('GENERIC_USER')")
    public void changePassword(@RequestBody ChangePasswordRequestModel changePasswordRequestModel,
                               Authentication authentication) throws PasswordDontMatchException, UserNotFoundException {
        changePasswordService.changePassword(changePasswordRequestModel, authentication.getName());
    }

    @PostMapping("/password/reset")
    public void resetPasswordRequest(@RequestBody ResetPasswordRequestModel resetPasswordRequestModel)
            throws UserNotFoundException {
        passwordResetService.resetPasswordRequest(resetPasswordRequestModel);
    }

    @PostMapping("/password/reset/{token}")
    public void resetPassword(@PathVariable String token, @RequestBody PasswordRequestModel passwordRequestModel)
            throws PasswordDontMatchException, UserNotFoundException {
        passwordResetService.resetPassword(token, passwordRequestModel);
    }
}
