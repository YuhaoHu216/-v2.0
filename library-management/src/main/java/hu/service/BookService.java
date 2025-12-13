package hu.service;

import hu.pojo.Book;
import hu.pojo.PageBean;
import hu.query.BookQuery;
import org.springframework.stereotype.Service;

//@Service
public interface BookService {

    //根据书名进行查询
    PageBean page(BookQuery bookQuery);

    //新增书籍
    void add(Book book);

    //删除书籍
    void delete(String name);

    //根据id查询书籍
    Book getById(Integer bookId);

    //更新书籍信息
    void update(Book book);


}
