package com.arthurneves.TaskManager.exceptions;

public class SameTeamName extends RuntimeException{
    public SameTeamName() {
        super("Você já possui um time com esse nome");
    }
}
