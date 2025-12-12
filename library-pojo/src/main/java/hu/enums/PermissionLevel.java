package hu.enums;

import lombok.Getter;

@Getter
public enum PermissionLevel {
    ADMIN("普通管理员"),
    SUPER_ADMIN("超级管理员");

    PermissionLevel(String type) {
        this.type = type;
    }

    private final String type;
}
