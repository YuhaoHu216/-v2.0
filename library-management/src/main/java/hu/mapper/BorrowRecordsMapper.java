package hu.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;

@Mapper
public interface BorrowRecordsMapper {
    @Insert("insert into borrow_records(reader_id,book_id,borrow_date,due_date) " +
            "value (#{readerId},#{bookId},#{borrowDate},#{dueDate})")
    void insert(Integer readerId, Integer bookId, LocalDate borrowDate, LocalDate dueDate);

    @Update("update borrow_records set return_date = #{returnDate},status=2 where reader_id = #{readerId} and book_id = #{bookId}")
    void updateStatus(Integer bookId, Integer readerId, LocalDate returnDate);
}
