package hu.interceptor;

import hu.pojo.Admin;
import hu.pojo.Reader;
import hu.utils.JwtUtils;
import hu.utils.ReaderHolder;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private ReaderHolder readerHolder;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        String token = request.getHeader("Authorization");
        if (token == null) {
            return false;
        }

        Claims claims = JwtUtils.parseJwt(token);
        if(claims.containsKey("readerId")){
            Reader reader = new Reader();
            reader.setReaderId((Integer) claims.get("readerId"));
            reader.setReaderName((String) claims.get("readerName"));
            // 在“当前请求线程”中放入 ThreadLocal
            readerHolder.setReader(reader);
        }else if (claims.containsKey("adminId")){
            Admin admin = new Admin();
            admin.setAdminId((Integer) claims.get("adminId"));
            admin.setUsername((String) claims.get("username"));
            admin.setAdminType(( Integer) claims.get("adminType"));
            readerHolder.setReader(admin);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) {
        // ⭐ 非常重要，防止内存泄漏
        readerHolder.removeReader();
    }
}
