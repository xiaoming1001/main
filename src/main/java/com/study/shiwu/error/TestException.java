package com.study.shiwu.error;

import com.study.shiwu.response.ResponseBody;
import com.study.shiwu.response.ResponseStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常处理器
 * @ControllerAdvice对异常进行统一处理，抛出全局的异常，拦截到异常就替代controll中正确值返回的消息
 * */
@ControllerAdvice
public class TestException {
    //统一处理抛出的异常，必须定义一个方法
    //ResponseBody(z.getResponseStatus()    返回的事自定义的异常类ResponseBody，而不是Spring 自带的那个
    @ExceptionHandler(ZengError.class)
    @org.springframework.web.bind.annotation.ResponseBody
    public ResponseBody zengmingException(ZengError z){
        z.printStackTrace();
        return new ResponseBody(z.getResponseStatus());
    }

    //如果不是定义好了的定义的异常，统一抛出失败
    @ExceptionHandler(Exception.class)
    @org.springframework.web.bind.annotation.ResponseBody
    public ResponseBody allException(Exception e){
        e.printStackTrace();
        return new ResponseBody(ResponseStatus.ERRORS);
    }
}
