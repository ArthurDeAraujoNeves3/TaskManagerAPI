package com.arthurneves.TaskManager.exceptions;

public class UserFoundException extends RuntimeException{
    public UserFoundException() {
        super("Email/Username já cadastrados");
    }
}
