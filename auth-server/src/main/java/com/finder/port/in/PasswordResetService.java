package com.finder.port.in;

import com.finder.exceprions.PasswordDontMatchException;
import com.finder.exceprions.UserNotFoundException;
import com.finder.port.models.request.PasswordRequestModel;
import com.finder.port.models.request.ResetPasswordRequestModel;

public interface PasswordResetService {
    void resetPasswordRequest(ResetPasswordRequestModel resetPasswordRequestModel)
            throws UserNotFoundException;
    void resetPassword(String token, PasswordRequestModel password)
            throws PasswordDontMatchException, UserNotFoundException;
}
