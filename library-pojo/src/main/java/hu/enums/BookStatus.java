package hu.enums;

import lombok.Getter;

@Getter
public enum BookStatus {

    AVAILABLE("在馆"),
    BORROWED("借出");
    BookStatus(String status){
        this.status = status;
    }
    private final String status;
}
