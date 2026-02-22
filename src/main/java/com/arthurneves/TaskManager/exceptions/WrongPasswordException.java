package com.arthurneves.TaskManager.exceptions;

public class WrongPasswordException extends RuntimeException{
    public WrongPasswordException() {
        super("Senha ou email incorretos");
    }
}
