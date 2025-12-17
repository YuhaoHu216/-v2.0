package hu.service.impl;

import hu.mapper.AdminMapper;
import hu.pojo.Admin;
import hu.service.AdminService;
import hu.utils.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminMapper adminMapper;


    //管理员登录
    @Override
    public Admin login(Admin admin) {
        admin.setPassword(HashUtil.md5(admin.getPassword()));
        return adminMapper.getByAdminNameAndPassword(admin);
    }

    @Override
    public int add(Admin admin) {
        admin.setPassword(HashUtil.md5(admin.getPassword()));
        return adminMapper.insert(admin);
    }

    @Override
    public int delete(Integer adminId) {
        return adminMapper.delete(adminId);
    }
}
