package com.study.shiwu.controller;    /**
 * @author: wxs
 * @date: 2020/3/12
 */

import com.study.shiwu.entity.Use;
import com.study.shiwu.response.ResponseBody;
import com.study.shiwu.response.ResponseStatus;
import com.study.shiwu.serviceimp.ServiceImp;
import com.study.shiwu.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zm
 * @date 2020/3/12 14:51
 */
@Api(tags = "测试Token")
@RestController
public class TokenControll {
    @Autowired
    private ServiceImp si;

    @PostMapping("testToken")
    public ResponseBody test3(String account,String pwd){
        Use u=si.selectUser(account, pwd);
        account=u.getAccount();
        //登录成功，将token返回给浏览器
        ResponseBody responseBody=new ResponseBody(ResponseStatus.SUCCESS,JwtTokenUtil.createUserToken(account));
        return responseBody;
    }

}
