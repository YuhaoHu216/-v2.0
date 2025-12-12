//package hu.interceptor;
//
//import com.alibaba.fastjson.JSONObject;
//import hu.pojo.Result;
//import hu.utils.JwtUtils;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//@Slf4j
//@Component
//public class LoginCheckInterceptor implements HandlerInterceptor {
//    @Override //目标资源方法运行前运行,返回true:放行,返回false:不放行
//    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
//        System.out.println("praHandle ...");
//
//
//        //1.获取请求url
//        String url = req.getRequestURI().toString();
//        log.info("请求的url:{}",url);
//
//        //2.判断url中是否包含login,如果包含,说明为登录操作,放行
//        if(url.contains("login")) {
//            log.info("登录操作,放行...");
//
//            return true;
//        }
//        //3.获取请求头的令牌
//        String jwt = req.getHeader("token");
//        //4.判断令牌是否存在,如不存在,则返回错误信息
//        if(!StringUtils.hasLength(jwt)){
//            log.info("请求头token为空,返回未登录的信息");
//            Result error = Result.error("NOT_LOGIN");
//            //手动转换,将对象转化为json格式数据返回----->阿里巴巴提供的fastJSON工具包,导入依赖
//            String notLogin = JSONObject.toJSONString(error);
//            resp.getWriter().write(notLogin);
//            return false;
//        }
//        //5.解析token,如解析失败,返回错误结果
//        try {
//            JwtUtils.parseJwt(jwt);
//        } catch (Exception e) {  //jwt解析失败
//            e.printStackTrace();
//            log.info("解析令牌失败,返回未登录信息");
//            Result error = Result.error("NOT_LOGIN");
//
//            String notLogin = JSONObject.toJSONString(error);
//            resp.getWriter().write(notLogin);
//            return false;
//        }
//        //6.放行
//        log.info("登录校验成功");
//
//        return true;
//    }
//
//    @Override //目标资源方法运行后运行
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("postHandle ...");
//
//    }
//
//    @Override //视图渲染完毕后运行,最后运行
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        System.out.println("afterCompletion...");
//    }
//}
