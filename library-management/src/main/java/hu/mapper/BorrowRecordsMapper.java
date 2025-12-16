package hu.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;

@Mapper
public interface BorrowRecordsMapper {
    @Insert("insert into borrow_records(reader_id,book_id,borrow_date,due_date) " +
            "value (#{readerId},#{bookId},#{borrowDate},#{dueDate})")
    void insert(Integer readerId, Integer bookId, LocalDate borrowDate, LocalDate dueDate);
}
