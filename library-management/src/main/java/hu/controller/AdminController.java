package hu.controller;


import hu.pojo.Admin;
import hu.pojo.Result;
import hu.query.AdminQuery;
import hu.service.AdminService;
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
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Resource
    private ReaderHolder readerHolder;
    //管理员登录
    @PostMapping("/login")
    public Result login(@RequestBody Admin admin){
        log.info("管理员登录:{}",admin);
        Admin a = adminService.login(admin);

        //登录成功,生成并下发令牌
        if (a != null){
            Map<String,Object> claims = new HashMap<>();
            claims.put("adminId",a.getAdminId());
            claims.put("username",a.getUsername());
            claims.put("adminType",a.getAdminType());
            String jwt = JwtUtils.generateJwt(claims); //令牌包含管理员信息
            return Result.success(jwt);
        }
        //登录失败返回错误信息
        return Result.error("用户名或密码错误");
    }

    // 根据token获取当前管理员信息
    @GetMapping("/me")
    public Result me(){
        return Result.success(readerHolder.getReader());
    }

    // 系统管理员新增管理员
    @PostMapping("/add")
    public Result add(@RequestBody Admin admin){
        log.info("添加管理员:{}",admin);
        int result = adminService.add(admin);
        if (result == 1){
            return Result.success("添加成功");
        }
        return Result.error("添加失败");
    }

    @PostMapping("/page/list")
    public Result page(@RequestParam(defaultValue = "1") Integer page,      //设定默认值
                       @RequestParam(defaultValue = "5") Integer pageSize,
                       @RequestBody AdminQuery admin){
        log.info("分页查询管理员:{}",admin);
        return adminService.page(page,pageSize,admin);
    }


    // 系统管理员删除管理员
    @DeleteMapping("/delete")
    public Result delete(Integer adminId){
        log.info("删除管理员,id:{}",adminId);
        int result = adminService.delete(adminId);
        if (result == 1){
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }


}
