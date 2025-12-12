package hu.service;

import hu.pojo.Book;
import hu.pojo.PageBean;
import org.springframework.stereotype.Service;

@Service//表记服务类
public interface BookService {

    //根据书名进行查询
    PageBean page(Integer page, Integer pageSize, String name);

    PageBean borrow(Integer page, Integer pageSize, String name);


    //新增书籍
    void add(Book book);

    //删除书籍
    void delete(String name);

    //根据id查询书籍
    Book getById(Integer id);

    //更新书籍信息
    void update(Book book);


}
