package com.finder.port.in;

import com.finder.exceprions.PasswordDontMatchException;
import com.finder.exceprions.UserNotFoundException;
import com.finder.port.models.request.ChangePasswordRequestModel;

public interface ChangePasswordService {
    void changePassword(ChangePasswordRequestModel resetPassword, String initiatorUsername)
            throws PasswordDontMatchException, UserNotFoundException;
}
