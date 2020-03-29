package com.study.shiwu.interceptor;    /**
 * @author: wxs
 * @date: 2020/3/11
 */
import com.study.shiwu.util.JwtTokenUtil;
import io.swagger.annotations.ApiModel;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zm
 * @date 2020/3/11 16:08
 * @description 拦截器
 */
@ApiModel("拦截token")
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //System.out.println("请求方式：："+request.getMethod());
        System.out.println("拦截到的token："+request.getHeader("token"));
        String token=request.getHeader("token");
        //验证是否有token***
        //JwtTokenUtil.verifyToken(token);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

}
