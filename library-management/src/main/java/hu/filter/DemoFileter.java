package hu.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.util.logging.LogRecord;

//@WebFilter(urlPatterns = "/*")
public class DemoFileter implements Filter {  //新版本的jakarta.servlet下的Filter
    @Override
    public void init(FilterConfig filterConfig) throws ServletException{
        System.out.println("init 初始化执行了");
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException,ServletException{
        System.out.println("Demo拦截到了请求");
        //放行
        chain.doFilter(request,response);
    }

    @Override
    public void destroy(){
        System.out.println("destory 销毁方法执行了");
    }


}
