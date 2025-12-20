package hu.mapper;

import hu.pojo.Admin;
import hu.query.AdminQuery;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper//将接口生成动态代理类
public interface AdminMapper {
    //管理员登录
    @Select("select * from admin where username = #{username} and password_hash = #{password}")
    Admin getByAdminNameAndPassword(Admin admin);

    @Insert("insert into admin(username,password_hash,real_name) values(#{username},#{password},#{realName})")
    int insert(Admin admin);

    @Delete("delete from admin where admin_id = #{adminId}")
    int delete(Integer adminId);

    // 分类查询
    List<Admin> list(AdminQuery admin);
}
