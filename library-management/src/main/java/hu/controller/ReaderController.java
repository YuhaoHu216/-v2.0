package hu.controller;

import hu.pojo.PageBean;
import hu.pojo.Result;
import hu.pojo.Reader;
import hu.service.UserService;
import hu.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/readers")
public class ReaderController {
    @Autowired
    private UserService userService;

    //分页查询用户信息
    @GetMapping("/page/list")
    public Result page(@RequestParam(defaultValue = "1") Integer page,      //设定默认值
                       @RequestParam(defaultValue = "5") Integer pageSize,
                       Integer id){
        log.info("分页查询,参数:{},{},{}",page,pageSize,id);
        //调用service进行分页查询操作
        PageBean pageBean = userService.page(page,pageSize,id);
        return Result.success(pageBean);
    }

    //根据id删除用户
    @DeleteMapping()
    public Result delete(Integer id){
        log.info("删除用户,id:{}",id);
        userService.delete(id);
        return Result.success();
    }

    //用户借阅书籍
    @PostMapping()
    public Result borrow(String name){
        log.info("借书,书名:{}",name);
        userService.borrow(name);
        return Result.success();
    }

    //用户归还书籍
    @PutMapping()
    public Result drop(String name){
        log.info("还书,书名:{}",name);
        userService.drop(name);
        return Result.success();
    }

    //用户注册
    @GetMapping("/register")
    public Result register(@RequestBody Reader reader){
        log.info("用户注册,用户:{}", reader);
        userService.register(reader);
        return Result.success();
    }

    //用户登录
    @GetMapping("/login")
    public Result login(@RequestBody Reader reader){
        log.info("用户登录:{}", reader);
        Reader u = userService.login(reader);

        //登录成功,生成并下发令牌
        if (u != null){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",u.getId());
            claims.put("name",u.getName());

            String jwt = JwtUtils.generateJwt(claims); //令牌包含用户信息
            return Result.success(jwt);
        }
        //登录失败返回错误信息
        return Result.error("用户名或密码错误");
    }
}
