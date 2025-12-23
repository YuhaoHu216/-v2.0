package hu.config;

import hu.interceptor.LoginInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private LoginInterceptor loginInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 本地路径和 URL 映射
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:D:/upload/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor) // 用注入，别 new
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/**/login",
                        "/**/register",
                        "/images/**",
                        "/upload"
                );
    }

}