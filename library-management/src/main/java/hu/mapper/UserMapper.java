package hu.mapper;

import hu.pojo.Reader;
import hu.query.ReaderQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper//将接口生成动态代理类
public interface UserMapper {
    //用户分页查询
    List<Reader> list(ReaderQuery readerQuery);

    //删除用户
    @Delete("delete from readers where reader_id = #{readerId}")
    void delete(Integer id);

    //借阅书籍
    @Update("update readers set current_borrow_count = current_borrow_count + 1 where reader_id = #{readerId}")
    void borrow(Integer readerId);

    //还书
    @Update("update readers set current_borrow_count = current_borrow_count - 1 where reader_id = #{readerId}")
    void drop(Integer readerId);

    //用户注册
    @Options(useGeneratedKeys = true,keyProperty = "readerId",keyColumn = "reader_id")
    @Insert("insert into readers(reader_name,password,reader_type,department,phone_number,account) " +
            "values (#{readerName},#{password},#{readerType},#{department},#{phoneNumber},#{account})")
    void rigister(Reader reader);

    //查找用户
    @Select("select * from readers where account = #{account} and password = #{password}")
    Reader getByUsernameAndPassword(Reader reader);

    @Select("select * from readers where reader_id = #{readerId}")
    Reader getById(Reader reader);
}
