package hu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hu.mapper.UserMapper;
import hu.pojo.PageBean;
import hu.pojo.Reader;
import hu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//实现类，用来实现方法

@Service//标记服务类
public class UserServiceIpml implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize, Integer id) {
        //设置分页参数
        PageHelper.startPage(page,pageSize);

        //执行查询
        List<Reader> readerList = userMapper.list(id);

        //获取分页结果
        Page<Reader> p = (Page<Reader>) readerList;

        //封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return pageBean;

    }

    //根据id删除用户
    @Override
    public void delete(Integer id) {
        userMapper.delete(id);
    }

    //借阅书籍
    @Override
    public void borrow(String name) {
        userMapper.borrow(name);
    }

    //还书
    @Override
    public void drop(String name) {
        userMapper.drop(name);
    }

    @Override
    public void register(Reader reader) {
        userMapper.rigister(reader);
    }

    @Override
    public Reader login(Reader reader) {
        return userMapper.getByUsernameAndPassword(reader);
    }
}
