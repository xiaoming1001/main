package com.study.shiwu.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author: wxs
 * @date: 2020/3/13
 */
@Mapper
public interface UserDao {
    void addUser1(String account);
}
