package hu.service.impl;

import hu.mapper.AdminMapper;
import hu.pojo.Admin;
import hu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminMapper adminMapper;


    //管理员登录
    @Override
    public Admin login(Admin admin) {
        return adminMapper.getByAdminnameAndPassword(admin);
    }
}
