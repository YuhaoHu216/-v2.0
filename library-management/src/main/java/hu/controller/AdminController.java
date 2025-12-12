package hu.controller;


import hu.pojo.Admin;
import hu.pojo.Result;
import hu.service.AdminService;
import hu.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j //便于生成日志
@RestController//处理http请求以及返回json格式数据
public class AdminController {

    @Autowired
    private AdminService adminService;
    //管理员登录
    @GetMapping("/adminlogin")
    public Result login(@RequestBody Admin admin){
        log.info("用户登录:{}",admin);
        Admin a = adminService.login(admin);

        //登录成功,生成并下发令牌
        if (a != null){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",a.getId());
            claims.put("name",a.getName());

            String jwt = JwtUtils.generateJwt(claims); //令牌包含管理员信息
            return Result.success(jwt);
        }
        //登录失败返回错误信息
        return Result.error("用户名或密码错误");
    }


}
