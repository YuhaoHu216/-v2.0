package hu.service.impl;

import hu.mapper.AdminMapper;
import hu.pojo.Admin;
import hu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//实现类，用来实现方法

@Service//标记服务类
public class AdminServiceIpml implements AdminService{
    @Autowired
    private AdminMapper adminMapper;


    //管理员登录
    @Override
    public Admin login(Admin admin) {
        return adminMapper.getByAdminnameAndPassword(admin);
    }
}
