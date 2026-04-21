package com.example.scheduleparser.exception;

public class GroupNotFoundException extends RuntimeException {
    public GroupNotFoundException(String group) {
        super("Группа не найдена: " + group);
    }
}
