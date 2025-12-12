package hu.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
public class ABCFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("ABC 拦截到请求   放行前逻辑");
        chain.doFilter(request,response);
        System.out.println("ABC 拦截到请求   放行后逻辑");

    }
}
