package com.lntu.service;

import com.lntu.entity.Order;
import com.lntu.entity.OrderProduct;

import java.util.List;

public interface OrderService {
    // 添加订单
    Integer insertOrder(Order order);
    // 统计订单状态
    Integer countOrderStatus(Integer uid,Integer status);
    // 修改订单
    Integer cancelOrder(Integer id);
    // 按id查询订单
    Order selectOrderById(Integer id);
}