package com.finder.exceprions;

public class UserIsBlockedException extends Exception {
    public UserIsBlockedException(String user){
        super(String.format("User with username '%s' is Blocked", user));
    }
}
