package com.arthurneves.TaskManager.exceptions;

public class NotTeamOwner extends RuntimeException {
    public NotTeamOwner() {
        super("Você não é dono desse time");
    }
}
