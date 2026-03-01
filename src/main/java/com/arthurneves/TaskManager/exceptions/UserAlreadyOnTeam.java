package com.arthurneves.TaskManager.exceptions;

public class UserAlreadyOnTeam extends RuntimeException {
    public UserAlreadyOnTeam() {
        super("Usuário já está no time");
    }
}
