package com.lntu.dao;


import com.lntu.entity.Order;
import com.lntu.view.OrderDetailViewData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    // 增加订单
    Integer insertOrder(Order order);
    // 按id修改订单
    Integer updateOrderById(Order order);
    // 统计订单状态
    Integer countOrderStatus(@Param(value = "uid")Integer uid,@Param(value = "status")Integer status);
    // 按id查询订单
    Order selectOrderById(@Param(value = "id")Integer id);
}