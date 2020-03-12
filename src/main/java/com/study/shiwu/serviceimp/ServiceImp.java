package com.study.shiwu.serviceimp;

import com.study.shiwu.dao.DaoInt;
import com.study.shiwu.entity.Use;
import com.study.shiwu.entity.User;
import com.study.shiwu.error.TestException;
import com.study.shiwu.error.ZengError;
import com.study.shiwu.response.ResponseBody;
import com.study.shiwu.response.ResponseStatus;
import com.study.shiwu.serviceint.ServiceInt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Reader;
import java.sql.SQLException;

/**
 * @author ASUS
 */
@Service
public class ServiceImp implements ServiceInt {
    @Autowired
    private DaoInt dao;

    private static final Logger log= LoggerFactory.getLogger(ServiceImp.class);

    @Transactional(rollbackFor = Exception.class)
    public String update1(String card1,String card2,double money) {
        String str="";
        //通过账号获得卡内余额
      User user=dao.select1(card1);
      User user1=dao.select1(card2);
      log.info("原账号"+card1+"的余额是"+user.getMoney());
      log.info("被转账的账号"+card2+"的余额是"+user.getMoney());
        //进行金额加减
        if (user.getMoney()>money){
            double money1=user.getMoney()-money;
            double money2=user1.getMoney()+money;


            dao.update1(card1, money1);
            //int i=1/0;
            dao.update1(card2, money2);
            str="200";
        }else {
            str="201";
        }

       return str;
    }


    public User select1(String card){
        if (card.equals("1002")){
            throw new ZengError(ResponseStatus.MONEY_BU_ZU);
        }
        return dao.select1(card);
    }


    public void addUser(User user){
        if (user.getCard().equals("1003")){
            System.out.println("只有等于1003才执行，这个异常方法");
            throw new ZengError(ResponseStatus.TONG_MING);
        }
        dao.addUser(user);
    }

    public void updateUser(String card,double money){
        dao.updateUser(card,money);
    }

    //测试test'
    public Use selectUser(String account,String pwd){
        return  dao.selectUser(account, pwd);
    }

}
