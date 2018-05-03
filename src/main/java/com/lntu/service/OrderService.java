package com.lntu.service;

import com.lntu.entity.Order;

public interface OrderService {
    // 添加订单
    Integer insertOrder(Order order);
}
