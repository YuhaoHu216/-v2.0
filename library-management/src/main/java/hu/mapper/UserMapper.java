package hu.mapper;

import hu.pojo.Reader;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper//将接口生成动态代理类
public interface UserMapper {
    //将用户分页查询
    @Select("select * from user where id = #{id}")
    List<Reader> list(Integer id);

    //删除用户
    @Delete("delete from user where id = #{id}")
    void delete(Integer id);

    //借阅书籍
    @Update("update book set borrowed = 1 where name = #{name}")
    void borrow(String name);

    //还书
    @Update("update book set borrowed = 0 where name = #{name}")
    void drop(String name);

    //用户注册
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    @Insert("insert into user(name,password) values (#{name},#{password})")
    void rigister(Reader reader);

    //查找用户
    @Select("select * from user where name = #{name} and password = #{password}")
    Reader getByUsernameAndPassword(Reader reader);
}
