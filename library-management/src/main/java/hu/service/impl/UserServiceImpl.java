package hu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hu.mapper.BookMapper;
import hu.mapper.BorrowRecordsMapper;
import hu.mapper.UserMapper;
import hu.pojo.Book;
import hu.pojo.PageBean;
import hu.pojo.Reader;
import hu.pojo.Result;
import hu.query.ReaderQuery;
import hu.service.UserService;
import hu.utils.ReaderHolder;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
//实现类，用来实现方法

@Service//标记服务类
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Resource
    private ReaderHolder readerHolder;
    @Resource
    private BorrowRecordsMapper borrowRecordsMapper;
    @Resource
    private BookMapper bookMapper;

    @Override
    public PageBean page(ReaderQuery  query) {
        //设置分页参数
        PageHelper.startPage(query.getPage(),query.getPageSize());

        //执行查询
        List<Reader> readerList = userMapper.list(query);

        //获取分页结果
        Page<Reader> p = (Page<Reader>) readerList;

        //封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return pageBean;

    }

    //根据id删除用户
    @Override
    public void delete(Integer readerId) {
        userMapper.delete(readerId);
    }

    //借阅书籍
    @Override
    @Transactional
    public Result borrow(Integer bookId) {
        Reader reader = (Reader) readerHolder.getReader();
        // 用户信息的更新
        // 1.判断是否已经借阅了该书籍
        Reader r = userMapper.getById(reader);
        if(r.getCurrentBorrowCount() >= r.getMaxBorrowCount()) {
            return Result.error("借阅数量已达上限");
        }
        userMapper.borrow(reader.getReaderId());
        // 借阅表信息的更新
        LocalDate borrowDate = LocalDate.now();
        LocalDate dueDate = borrowDate.plusDays(7);
        borrowRecordsMapper.insert(reader.getReaderId(),bookId,borrowDate,dueDate);
        // 图书表信息的更新
        // 1.查询图书余量
        Book b = bookMapper.getById(bookId);
        if(b.getAvailableCopies() <= 0) {
            return Result.error("没有可借阅的图书");
        }
        b.setAvailableCopies(b.getAvailableCopies() - 1);
        bookMapper.update(b);
        return Result.success();
    }

    //还书
    @Override
    @Transactional
    public Result drop(Integer bookId) {
        // 获取登录用户信息
        Reader reader = (Reader) readerHolder.getReader();
        Reader r = userMapper.getById(reader);
        // 读者信息更新
        r.setCurrentBorrowCount(r.getCurrentBorrowCount() - 1);
        userMapper.drop(r.getReaderId());
        // 借阅表信息更新
        LocalDate returnDate = LocalDate.now();
        borrowRecordsMapper.updateStatus(bookId,r.getReaderId(),returnDate);
        // 图书表信息更新
        bookMapper.drop(bookId);
        return Result.success();
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
