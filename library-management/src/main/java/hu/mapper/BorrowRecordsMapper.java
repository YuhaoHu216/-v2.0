package hu.mapper;

import hu.query.BorrowQuery;
import hu.vo.BorrowRecordsVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface BorrowRecordsMapper {
    @Insert("insert into borrow_records(reader_id,book_id,borrow_date,due_date) " +
            "value (#{readerId},#{bookId},#{borrowDate},#{dueDate})")
    void insert(Integer readerId, Integer bookId, LocalDate borrowDate, LocalDate dueDate);

    @Update("update borrow_records set return_date = #{returnDate},status=2 where reader_id = #{readerId} and book_id = #{bookId}")
    void updateStatus(Integer bookId, Integer readerId, LocalDate returnDate);

    /**
     * 查询借阅记录
     * @param query 查询参数
     * @return 借阅记录列表
     */
    List<BorrowRecordsVO> list(BorrowQuery query);
}
