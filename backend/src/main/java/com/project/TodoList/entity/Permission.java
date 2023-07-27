package com.project.TodoList.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    USER_READ("read"),
    USER_CREATE("create"),
    USER_UPDATE("update"),
    USER_DELETE("delete");

    private final String permission;

    public String getPermission() {
        return permission;
    }
}
