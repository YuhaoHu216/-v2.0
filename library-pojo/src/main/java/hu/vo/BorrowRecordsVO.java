package hu.vo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowRecordsVO {
    private Integer recordId;
    private Integer borrowStatus;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private Integer readerId;
    private String bookTitle;
    private String category;
}
