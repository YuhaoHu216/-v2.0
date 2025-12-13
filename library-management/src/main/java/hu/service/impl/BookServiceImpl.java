package hu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hu.mapper.BookMapper;
import hu.pojo.Book;
import hu.pojo.PageBean;
import hu.query.BookQuery;
import hu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    //根据书名分页查询
    @Override
    public PageBean page(BookQuery query) {
        //设置分页参数
        PageHelper.startPage(query.getPage(),query.getPageSize());

        //执行查询
        List<Book> bookList = bookMapper.list(query);

        //获取分页结果
        Page<Book> p = (Page<Book>) bookList;

        //封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }

    //新增书籍
    @Override
    public void add(Book book) {
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
        bookMapper.update(book);
    }
}
