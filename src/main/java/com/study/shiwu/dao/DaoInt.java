package com.study.shiwu.dao;

import com.study.shiwu.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ASUS
 */
@Mapper
public interface DaoInt {
    void update1(String card,double money);

    /**
     * @param card
     * @return
     * 查询原本金额
     */
    User select1(String card);

    void addUser(User user);

    void updateUser(String card,double money);

}
