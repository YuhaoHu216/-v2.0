package hu.query;

import lombok.Data;

@Data
public class BorrowQuery {
    private Integer recordId;
    private Integer borrowStatus;
    private Integer readerId;
    private String bookTitle;
    private String category;
    private Integer page = 1;
    private Integer pageSize = 10;
}
