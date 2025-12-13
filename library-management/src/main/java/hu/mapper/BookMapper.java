package hu.mapper;

import hu.pojo.Book;
import hu.query.BookQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper//表明这是持久层接口,将接口生成动态代理类
public interface BookMapper {

    /**
     * 分页查询书籍信息
     * @param query 查询条件
     * @return 书籍集合
     */
    List<Book> list(BookQuery  query);


    /**
     * 新增数据
     * @param book 新增书籍信息
     */
    @Insert("insert into books(isbn,title,author,publisher,publish_date,category,total_copies,available_copies,location,image) " +
            "VALUES (#{isbn},#{title},#{author},#{publisher},#{publishDate},#{category},#{totalCopies},#{totalCopies},#{location},#{image})")
    @Options(useGeneratedKeys = true,keyProperty = "bookId",keyColumn = "book_id")
    void insert(Book book);

    //删除书籍
    @Delete("delete from books where book_id = #{bookId}")
    void delete(String name);

    //根据id查询书籍
    @Select("select * from books where book_id = #{bookId}")
    Book getById(Integer bookId);

    //更新书籍
    void update(Book book);


}
