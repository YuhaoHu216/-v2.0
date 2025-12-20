package hu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hu.mapper.AdminMapper;
import hu.pojo.Admin;
import hu.pojo.PageBean;
import hu.pojo.Result;
import hu.query.AdminQuery;
import hu.service.AdminService;
import hu.utils.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminMapper adminMapper;


    //管理员登录
    @Override
    public Admin login(Admin admin) {
        admin.setPassword(HashUtil.md5(admin.getPassword()));
        // 修改last_login
        adminMapper.updateLastLogin(admin.getUsername(), LocalDate.now());
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

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param admin
     * @return
     */
    @Override
    public Result page(Integer page, Integer pageSize, AdminQuery admin) {
        PageHelper.startPage(page,pageSize);
        List<Admin> adminList = adminMapper.list(admin);
        Page<Admin> p = (Page<Admin>) adminList;
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return Result.success(pageBean);
    }
}
