package com.coderio.to_do_service.exceptions;

public class TaskNotFoundException extends RuntimeException{

    public TaskNotFoundException(Long id) {
        super("La tarea con id " + id + " no fue encontrado");
    }
}
