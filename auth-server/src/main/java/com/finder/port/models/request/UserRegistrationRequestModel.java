package com.finder.port.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequestModel {
    @NotBlank(message = "Username cannot be empty")
    private String username;
    @NotBlank(message = "Password cannot be empty")
    private String nickname;
    @NotBlank(message = "Password cannot be empty")
    private String password;
    @NotBlank(message = "Password cannot be empty")
    private String repeatPassword;
    @Email(message = "Email is not correct")
    private String mail;
}
