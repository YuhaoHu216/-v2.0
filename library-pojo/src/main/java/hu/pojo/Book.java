package hu.pojo;

import hu.enums.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data//
public class Book {
    private String isbn;
//    private String img;
    private Integer bookId;
    private String title;
    private String author;
    private String publisher;
    private LocalDate publishDate;
    private String category;
    private Integer totalCopies;
    private Integer availableCopies;
    private String location;
    private BookStatus bookStatus;
}
