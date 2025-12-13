package hu.controller;

import hu.pojo.Book;
import hu.pojo.PageBean;
import hu.pojo.Result;
import hu.query.BookQuery;
import jakarta.annotation.Resource;
import hu.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "*")
public class BookController {

    @Resource
    private BookService bookService;

    //分页查询书籍信息
    @PostMapping("/page")
    public Result page(@RequestBody BookQuery query){
        log.info("分页查询,参数:{},{},{}",query.getPage(),query.getPageSize(),query.getTitle());
        //调用service进行分页查询操作
        PageBean pageBean = bookService.page(query);
        return Result.success(pageBean);
    }

    //新增书籍
    @PostMapping()
    public Result add(@RequestBody Book book){
        log.info("新增书籍,book:{}",book);
        bookService.add(book);
        return Result.success();
    }

    //删除书籍 TODO 修改为批量删除
    @DeleteMapping()
    public Result delete(String bookId){
        log.info("删除书籍,name:{}",bookId);
        //调用service进行删除操作
        bookService.delete(bookId);
        return Result.success();
    }

    //根据id查询书籍(查询回显)
    @GetMapping("/{bookId}")
    public Result getById(@PathVariable Integer bookId){
        log.info("根据id查询书籍信息,id:{}",bookId);
        Book book = bookService.getById(bookId);
        return Result.success(book);
    }

    //更改书籍信息
    @PutMapping()
    public Result update(@RequestBody Book book){
        log.info("更新书籍信息:{}",book);
        //调用service进行更改操作
        bookService.update(book);
        return Result.success();

    }
}
