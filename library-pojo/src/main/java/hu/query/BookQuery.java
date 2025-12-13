package hu.query;

import hu.enums.BookStatus;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author hyh
 * @since 2025-12-13
 */
@Data
public class BookQuery {
    private String isbn;
    private Integer bookId;
    private String title;
    private String author;
    private String publisher;
    private LocalDate startDate;
    private LocalDate endDate;
    private String category;
    private Integer availableCopies;
    private Integer status;
    private Integer page = 1;
    private Integer pageSize = 10;
}
