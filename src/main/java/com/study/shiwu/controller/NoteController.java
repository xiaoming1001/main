package com.study.shiwu.controller;    /**
 * @author: wxs
 * @date: 2020/3/23
 */

import com.alibaba.fastjson.JSON;
import com.study.shiwu.response.ResponseBody;
import com.study.shiwu.response.ResponseStatus;
import com.study.shiwu.serviceimp.ServiceImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author zm
 * @date 2020/3/23 17:36
 */
@RestController
@Api(tags = "短信验证")
public class NoteController {
    @Autowired
    private ServiceImp si;

    @ApiOperation("获取验证码")
    @GetMapping("getNote")
    public ResponseBody getNote(String phone) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //System.out.println("获取到用户的IP："+request.getRemoteAddr());
        System.out.println(phone);
        HttpSession session = request.getSession();
       //返回一个json字符串
        String jsonObj= si.getNote(phone);
        //获取字符串里的obj参数--验证码
        String str= JSON.parseObject(jsonObj).getString("obj");
        //将验证码存入session
        session.setAttribute("a",str);
        return new ResponseBody(ResponseStatus.SUCCESS);
    }

    @ApiOperation("登录验证")
    @GetMapping("loginNote")
    public ResponseBody loginNote(String note){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String note1= (String) session.getAttribute("a");
        System.out.println("前端传过来的验证码："+note);
        System.out.println("获取session里的验证码："+note1);
        System.out.println("取得的sessionID："+session.getId());
        //比较前端传过来的字符串是否与我们报讯的一致
        if (note.equals(note1)){
            System.out.println("验证码正确!!!!!!!!!");
            session.removeAttribute("a");
        }else{
            System.out.println("验证码输入错误!!!!!!!!!");
            return new ResponseBody(ResponseStatus.NOTE_EXPIRE);
        }
        return new ResponseBody(ResponseStatus.SUCCESS);
    }
}
