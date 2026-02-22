package com.arthurneves.TaskManager.exceptions;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException() {
        super("Email não cadastrado");
    }
}
