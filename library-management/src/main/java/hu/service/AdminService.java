package hu.service;

import hu.pojo.Admin;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    //管理员登录
    Admin login(Admin admin);

    //添加图书管理员
    int add(Admin admin);

    //删除图书管理员
    int delete(Integer adminId);
}
