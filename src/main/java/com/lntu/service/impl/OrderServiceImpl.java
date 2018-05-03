package com.lntu.service.impl;

import com.lntu.dao.OrderMapper;
import com.lntu.entity.Order;
import com.lntu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    // 订单服务
    @Autowired
    private OrderMapper orderMapper;

    /**
     * @param order 添加的商品信息
     * */
    @Override
    public Integer insertOrder(Order order) {
        if(order != null){
            Integer result = orderMapper.insertOrder(order);
            return result;
        }
        return null;
    }

}
