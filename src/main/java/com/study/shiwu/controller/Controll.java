package com.study.shiwu.controller;

import com.study.shiwu.serviceint.ServiceInt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Controll {
    @Autowired
    private ServiceInt si;

    private static final Logger log= LoggerFactory.getLogger(Controll.class);

    @RequestMapping("test1")
    @ResponseBody
    public String update(String card1,String card2,double money){
        log.info("账户是："+card1);
        log.info("被转账的账户是："+card2);
        log.info("转账金额是："+money);
        String str=si.update1(card1,card2, money);
        log.info("控制层："+str);
        return str;
    }

    @RequestMapping("index")
    public String index(){return "index";}
}
