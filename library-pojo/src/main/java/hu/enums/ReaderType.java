package hu.enums;

import lombok.Getter;

@Getter
public enum ReaderType {
    STUDENT("学生"),
    TEACHER("教师"),
    OTHER("职工");

    private final String type;
    ReaderType(String type) {
        this.type = type;
    }
}
