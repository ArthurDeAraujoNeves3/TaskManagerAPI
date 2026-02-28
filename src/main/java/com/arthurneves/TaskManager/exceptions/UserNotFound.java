package com.arthurneves.TaskManager.exceptions;

public class UserNotFound extends RuntimeException{
    public UserNotFound() {
        super("Usuário não encontrado");
    }
}
