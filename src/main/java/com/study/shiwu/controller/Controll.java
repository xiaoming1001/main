package com.study.shiwu.controller;

import com.study.shiwu.entity.User;
import com.study.shiwu.serviceimp.ServiceImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.study.shiwu.response.ResponseBody;
import com.study.shiwu.response.ResponseStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "转账模块")
public class Controll {
    @Autowired
    private ServiceImp si;
    private static final Logger log= LoggerFactory.getLogger(Controll.class);

    @RequestMapping("test1")
    public String update(String card1,String card2,double money){
        log.info("账户是："+card1);
        log.info("被转账的账户是："+card2);
        log.info("转账金额是："+money);
        String str=si.update1(card1,card2, money);
        log.info("控制层："+str);
        return str;
    }

    @ApiOperation("查询数据")
    @PostMapping("test2")
    public ResponseBody select1(String card){
        log.info("控制层获取到的卡号："+card);
        User user=si.select1(card);
        return new ResponseBody<>(ResponseStatus.SUCCESS,user);
    }

    @ApiOperation("新增用户")
    @PostMapping("addUser")
    public ResponseBody addUser(User user){
        si.addUser(user);
        return new ResponseBody(ResponseStatus.SUCCESS);
    }

    @ApiOperation("修改用户")
    @PostMapping("updateUser")
    public ResponseBody updateUser(String card,double money){
        return new ResponseBody(ResponseStatus.SUCCESS);
    }




    @RequestMapping("index")
    public String index(){return "index";}
}
