package com.lntu.service.impl;

import com.lntu.dao.OrderMapper;
import com.lntu.entity.Order;
import com.lntu.enums.MallStatusEnum;
import com.lntu.exception.MallException;
import com.lntu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * @param  uid 用户id
     * @param status 状态
     * */
    @Override
    public Integer countOrderStatus(Integer uid, Integer status) {
        return orderMapper.countOrderStatus(uid,status);
    }

    /**
     * <pre>
     *     取消订单
     * </pre>
     * @param id 订单id
     * */
    @Override
    public Integer cancelOrder(Integer id) {
        Order order = orderMapper.selectOrderById(id);
        if(order != null){
            order.setDel(new Byte("1"));
            return orderMapper.updateOrderById(order);
        }
        throw new MallException(MallStatusEnum.CANCEL_ORDER_FAIL);
    }

    /**
     * @param id 订单id
     * */
    @Override
    public Order selectOrderById(Integer id) {
        return orderMapper.selectOrderById(id);
    }

}
