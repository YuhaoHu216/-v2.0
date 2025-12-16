package hu.service;

import hu.pojo.PageBean;
import hu.pojo.Reader;
import hu.pojo.Result;
import org.springframework.stereotype.Service;
@Service//标记服务类
public interface UserService {
    //根据id分页查找用户
    PageBean page(Integer page, Integer pageSize, Integer id);

    //根据id删除用户
    void delete(Integer readerId);

    //借阅书籍
    Result borrow(Integer bookId);

    //环书
    void drop(String name);

    //用户注册
    void register(Reader reader);

    //用户登录
    Reader login(Reader reader);
}
