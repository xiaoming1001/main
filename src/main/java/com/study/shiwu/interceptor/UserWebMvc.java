package com.study.shiwu.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * @author zm
 * @date 2020/3/11 16:26
 */
@Configuration
public class UserWebMvc implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加自定义的拦截器
        registry.addInterceptor(new UserInterceptor()).addPathPatterns("/**");
        System.out.println("拦截器已启动");
    }


}
