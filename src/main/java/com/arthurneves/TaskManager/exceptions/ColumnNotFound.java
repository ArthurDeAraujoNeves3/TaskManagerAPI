package com.arthurneves.TaskManager.exceptions;

public class ColumnNotFound extends RuntimeException {
    public ColumnNotFound(String name) {
        super(String.format("Coluna %s não encontrada", name));
    }
}
