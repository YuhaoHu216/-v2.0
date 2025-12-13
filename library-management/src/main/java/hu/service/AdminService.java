package hu.service;

import hu.pojo.Admin;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    //管理员登录
    Admin login(Admin admin);
}
