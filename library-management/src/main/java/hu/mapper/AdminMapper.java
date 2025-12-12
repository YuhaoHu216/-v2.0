package hu.mapper;

import hu.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper//将接口生成动态代理类
public interface AdminMapper {
    //管理员登录
    @Select("select * from admin where name = #{name} and password = #{password}")
    Admin getByAdminnameAndPassword(Admin admin);
}
