package com.study.shiwu.dao;

import com.study.shiwu.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: wxs
 * @date: 2020/3/23
 */
@Mapper
public interface OrderDao {
    //获取所有订单
    List<Order> getOrder();

    void addOrder(Order order);
}
