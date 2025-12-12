package hu.mapper;

import hu.pojo.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper//表明这是持久层接口,将接口生成动态代理类
public interface BookMapper {
    //根据书名分页查询书籍
    @Select("select * from book where name like concat('%',#{name},'%') order by update_time")
    List<Book> list(String name);

    //书名分页显示已借阅书籍
    @Select("select * from book where name like concat('%',#{name},'%') and borrowed = 1")
    List<Book> borrow(String name);


    //新增书籍
    @Insert("insert into book(img, name, press, add_time, update_time, borrowed) VALUES (#{img},#{name},#{press},#{addTime},#{updateTime},0)")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")// 自动将生成的主键值赋值给book对象的id属性
    void insert(Book book);

    //删除书籍
    @Delete("delete from book where name = #{name}")
    void delete(String name);

    //根据id查询书籍
    @Select("select * from book where id = #{id}")
    Book getById(Integer id);

    //更新书籍
    void update(Book book);


}
