package hu.service;

import hu.pojo.Admin;
import hu.pojo.Result;
import hu.query.AdminQuery;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    //管理员登录
    Admin login(Admin admin);

    //添加图书管理员
    int add(Admin admin);

    //删除图书管理员
    int delete(Integer adminId);

    //分页查询
    Result page(Integer page, Integer pageSize, AdminQuery admin);
}
