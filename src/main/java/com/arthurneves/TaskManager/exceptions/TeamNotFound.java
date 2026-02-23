package com.arthurneves.TaskManager.exceptions;

public class TeamNotFound extends RuntimeException {
    public TeamNotFound() {
        super("Time não existe");
    }
}
