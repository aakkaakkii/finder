package com.finder.exceprions;

public class PasswordDontMatchException extends Exception {
    public PasswordDontMatchException(){
        super("Password don't Match");
    }
}