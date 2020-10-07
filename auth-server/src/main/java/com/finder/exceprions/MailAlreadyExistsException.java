package com.finder.exceprions;

public class MailAlreadyExistsException extends Exception {
    public MailAlreadyExistsException(String mail){
        super(String.format("%s Mail Already Exists", mail));
    }
}
