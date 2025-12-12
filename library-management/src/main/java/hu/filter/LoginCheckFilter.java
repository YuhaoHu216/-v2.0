package hu.filter;

import com.alibaba.fastjson.JSONObject;
import hu.pojo.Result;
import hu.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

/*

    原始java web实现

 */
@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //1.获取请求url
        String url = req.getRequestURI().toString();
        log.info("请求的url:{}",url);

        //2.判断url中是否包含login,如果包含,说明为登录操作,放行
        if(url.contains("login")) {
            log.info("登录操作,放行...");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        //3.获取请求头的令牌
        String jwt = req.getHeader("token");
        //4.判断令牌是否存在,如不存在,则返回错误信息
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空,返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换,将对象转化为json格式数据返回----->阿里巴巴提供的fastJSON工具包,导入依赖
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }
        //5.解析token,如解析失败,返回错误结果
        try {
            JwtUtils.parseJwt(jwt);
        } catch (Exception e) {  //jwt解析失败
            e.printStackTrace();
            log.info("解析令牌失败,返回未登录信息");
            Result error = Result.error("NOT_LOGIN");

            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }
        //6.放行
        log.info("登录校验成功");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
