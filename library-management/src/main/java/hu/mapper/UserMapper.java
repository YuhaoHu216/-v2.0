package hu.mapper;

import hu.pojo.Reader;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper//将接口生成动态代理类
public interface UserMapper {
    //用户分页查询
    List<Reader> list(Integer id);

    //删除用户
    @Delete("delete from readers where reader_id = #{readerId}")
    void delete(Integer id);

    //借阅书籍
    @Update("update books set borrowed = 1 where name = #{name}")
    void borrow(String name);

    //还书
    @Update("update books set borrowed = 0 where name = #{name}")
    void drop(String name);

    //用户注册
    @Options(useGeneratedKeys = true,keyProperty = "readerId",keyColumn = "reader_id")
    @Insert("insert into readers(reader_name,password,reader_type,department,phone_number,account) " +
            "values (#{readerName},#{password},#{readerType},#{department},#{phoneNumber},#{account})")
    void rigister(Reader reader);

    //查找用户
    @Select("select * from readers where account = #{account} and password = #{password}")
    Reader getByUsernameAndPassword(Reader reader);
}
