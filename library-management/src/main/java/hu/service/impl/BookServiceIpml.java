package hu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hu.mapper.BookMapper;
import hu.pojo.Book;
import hu.pojo.PageBean;
import hu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
//实现类，用来实现方法

@Service//表记服务类
public class BookServiceIpml implements BookService {

    @Autowired
    private BookMapper bookMapper;

    //根据书名分页查询
    @Override
    public PageBean page(Integer page, Integer pageSize, String name) {
        //设置分页参数
        PageHelper.startPage(page,pageSize);

        //执行查询
        List<Book> bookList = bookMapper.list(name);

        //获取分页结果
        Page<Book> p = (Page<Book>) bookList;

        //封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return pageBean;

    }

    @Override
    public PageBean borrow(Integer page, Integer pageSize, String name) {
        //设置分页参数
        PageHelper.startPage(page,pageSize);

        //执行查询
        List<Book> bookList = bookMapper.borrow(name);

        //获取分页结果
        Page<Book> p = (Page<Book>) bookList;

        //封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }

    //新增书籍
    @Override
    public void add(Book book) {
        book.setAddTime(LocalDateTime.now());    //设置新增的时间
        book.setUpdateTime(LocalDateTime.now());

        bookMapper.insert(book);
    }

    //删除书籍
    @Override
    public void delete(String name) {
        bookMapper.delete(name);
    }

    //根据id查询书籍
    @Override
    public Book getById(Integer id) {
        return bookMapper.getById(id);
    }

    //更改书籍信息
    @Override
    public void update(Book book) {
        book.setUpdateTime(LocalDateTime.now()); //设置更新时间
        bookMapper.update(book);
    }
}
