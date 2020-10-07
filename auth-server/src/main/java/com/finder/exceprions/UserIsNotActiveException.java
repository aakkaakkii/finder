package com.finder.exceprions;

public class UserIsNotActiveException extends Exception {
    public UserIsNotActiveException(String user) {
        super(String.format("User with username '%s' is not active", user));
    }
}
