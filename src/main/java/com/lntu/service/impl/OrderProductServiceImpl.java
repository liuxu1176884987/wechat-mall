package com.lntu.service.impl;

import com.lntu.dao.OrderProductMapper;
import com.lntu.entity.OrderProduct;
import com.lntu.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductServiceImpl implements OrderProductService {

    // 订单商品服务
    @Autowired
    private OrderProductMapper orderProductMapper;

    /**
     * @param orderProductList 订单中的商品
     * */
    @Override
    public Integer insertOrderProduct(List<OrderProduct> orderProductList) {
        if(orderProductList != null){
            return orderProductMapper.insertOrderProduct(orderProductList);
        }
        return null;
    }

}
