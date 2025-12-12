package hu.enums;

import lombok.Getter;

@Getter
public enum ReaderStatus {
    NORMAL("正常"),
    LOST("丢失"),
    LOGOUT("注销");

    private final String type;
    ReaderStatus(String type) {
        this.type = type;
    }
}
