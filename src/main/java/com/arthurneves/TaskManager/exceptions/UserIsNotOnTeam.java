package com.arthurneves.TaskManager.exceptions;

public class UserIsNotOnTeam extends RuntimeException{
    public UserIsNotOnTeam() {
        super("Você não faz parte desse time");
    }
}
