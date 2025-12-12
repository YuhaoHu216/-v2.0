package hu.service;

import hu.pojo.Admin;
import hu.pojo.PageBean;
import org.springframework.stereotype.Service;

@Service//标记服务类
public interface AdminService {

    //管理员登录
    Admin login(Admin admin);
}
