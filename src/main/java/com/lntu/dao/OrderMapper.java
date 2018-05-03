package com.lntu.dao;


import com.lntu.entity.Order;

public interface OrderMapper {
    // 增加订单
    Integer insertOrder(Order order);
    // 按id修改订单
    Integer updateOrderById(Order order);
}