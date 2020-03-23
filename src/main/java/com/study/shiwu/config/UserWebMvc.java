package com.study.shiwu.config;

import com.study.shiwu.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * @author zm
 * @date 2020/3/11 16:26
 */
@Configuration
public class UserWebMvc implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:" + System.getProperty("user.home") + "/images/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加自定义的拦截器   excludePathPatterns("/testToken")哪些方法不被拦截
        registry.addInterceptor(new UserInterceptor()).addPathPatterns("/**").excludePathPatterns("/testToken","/swagger-ui.html/**");
        System.out.println("拦截器已启动");
    }


}
