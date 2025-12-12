package hu.pojo;

import hu.enums.BorrowRecordsStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BorrowRecords {
    private Integer recordId;
    private Integer readerId;
    private Integer bookId;
    private LocalDate borrowTime;
    private LocalDate dueDate;
    private LocalDate returnTime;
    private Integer renewCount;
    private BorrowRecordsStatus status;
    private BigDecimal fineAmount;
}
