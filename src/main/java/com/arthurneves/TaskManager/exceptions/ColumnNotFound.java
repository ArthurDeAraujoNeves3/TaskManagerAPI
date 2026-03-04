package com.arthurneves.TaskManager.exceptions;

public class ColumnNotFound extends RuntimeException {
    public ColumnNotFound() {
        super("Coluna não encontrada");
    }
}
