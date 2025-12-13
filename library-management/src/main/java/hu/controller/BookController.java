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
    public Result delete(String name){
        log.info("删除书籍,name:{}",name);
        //调用service进行删除操作
        bookService.delete(name);
        return Result.success();
    }

    //根据id查询书籍(查询回显)
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询书籍信息,id:{}",id);
        Book book = bookService.getById(id);
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
