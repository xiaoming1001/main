package com.study.shiwu.dao;

import com.study.shiwu.entity.Use;
import com.study.shiwu.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ASUS
 */
@Mapper
public interface DaoInt {
    void update1(String card,double money);

    //测试多数据源一号
    User select1(String card);

    void addUser(User user);

    void updateUser(String card,double money);

    Use selectUser(String account,String pwd);

    //测试多数据源一号
    void add1(Use use);

}
