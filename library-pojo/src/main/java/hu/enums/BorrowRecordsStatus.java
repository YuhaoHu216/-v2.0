package hu.enums;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
public enum BorrowRecordsStatus {
    BORROWING("借阅中"),
    RETURNED("已归还"),
    OVERDUE("逾期");

    private final String type;
    BorrowRecordsStatus(String type) {
        this.type = type;
    }
}
