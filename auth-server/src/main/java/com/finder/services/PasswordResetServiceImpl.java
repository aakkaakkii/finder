package com.finder.services;

import com.finder.domain.User;
import com.finder.exceprions.UserNotFoundException;
import com.finder.port.in.ChangePasswordService;
import com.finder.port.in.PasswordResetService;
import com.finder.port.models.request.ChangePasswordRequestModel;
import com.finder.port.models.request.PasswordRequestModel;
import com.finder.port.models.request.ResetPasswordRequestModel;
import com.finder.port.out.MailSenderPort;
import com.finder.port.out.UserPort;
import com.finder.utils.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService, ChangePasswordService {
    private final UserPort userPort;
    private final MailSenderPort mailSenderPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void changePassword(ChangePasswordRequestModel changePassword, String initiatorUsername)
            throws UserNotFoundException {
        User user = userPort.getByUsername(changePassword.username);

        if (user != null && user.getUsername().equals(initiatorUsername)
                && passwordEncoder.matches(changePassword.oldPassword, user.getPassword())
                && changePassword.newPassword.equals(changePassword.repeatPassword)) {
            user.setPassword(passwordEncoder.encode(changePassword.newPassword));
            userPort.update(user);
        }
    }

    @Override
    public void resetPasswordRequest(ResetPasswordRequestModel resetPassword) throws UserNotFoundException {
        User user = userPort.getByEmail(resetPassword.email);

        if (user != null) {
            mailSenderPort.sendRestoreMail(user.createToken(), user.getEmail());
        }
    }

    @Override
    public void resetPassword(String token, PasswordRequestModel password) throws UserNotFoundException {
        User user = userPort.getByUsername(JwtTokenProvider.getUsername(token));

        if (user != null && password.password.equals(password.repeatPassword)) {
            user.setPassword(passwordEncoder.encode(password.password));
            userPort.update(user);
        }
    }
}
