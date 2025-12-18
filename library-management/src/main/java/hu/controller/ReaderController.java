package hu.controller;

import hu.pojo.PageBean;
import hu.pojo.Result;
import hu.pojo.Reader;
import hu.service.UserService;
import hu.utils.JwtUtils;
import hu.utils.ReaderHolder;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/readers")
@CrossOrigin(origins = "*")
public class ReaderController {
    @Autowired
    private UserService userService;

    @Resource
    private ReaderHolder readerHolder;

    //分页查询用户信息
    @GetMapping("/page/list")
    public Result page(@RequestParam(defaultValue = "1") Integer page,      //设定默认值
                       @RequestParam(defaultValue = "5") Integer pageSize,
                       @RequestParam("readerId") Integer id){
        log.info("分页查询,参数:{},{},{}",page,pageSize,id);
        //调用service进行分页查询操作
        PageBean pageBean = userService.page(page,pageSize,id);
        return Result.success(pageBean);
    }

    //根据id删除用户
    @DeleteMapping()
    public Result delete(Integer readerId){
        log.info("删除用户,id:{}",readerId);
        userService.delete(readerId);
        return Result.success();
    }

    //用户借阅书籍
    @PostMapping("/borrow")
    public Result borrow(Integer bookId){
        log.info("借书,书id:{}",bookId);
        return userService.borrow(bookId);
    }

    //用户归还书籍
    @PutMapping("/return")
    public Result drop(Integer bookId){
        log.info("还书,书id:{}",bookId);
        return userService.drop(bookId);
    }

    //用户注册
    @GetMapping("/register")
    public Result register(@RequestBody Reader reader){
        log.info("用户注册,用户:{}", reader);
        userService.register(reader);
        return Result.success();
    }

    //用户登录
    @PostMapping("/login")
    public Result login(@RequestBody Reader reader){
        log.info("用户登录:{}", reader);
        Reader u = userService.login(reader);

        //登录成功,生成并下发令牌
        if (u != null){
            Map<String,Object> claims = new HashMap<>();
            claims.put("readerId",u.getReaderId());
            claims.put("readerName",u.getReaderName());

            String jwt = JwtUtils.generateJwt(claims); //令牌包含用户信息
            return Result.success(jwt);
        }
        //登录失败返回错误信息
        return Result.error("用户名或密码错误");
    }

    @GetMapping("/me")
    public Result me(){
        Reader reader = (Reader) readerHolder.getReader();
        return Result.success(reader);
    }
}
