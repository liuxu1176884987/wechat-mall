package com.lntu.service.impl;

import com.lntu.dao.OrderProductMapper;
import com.lntu.entity.OrderProduct;
import com.lntu.service.OrderProductService;
import com.lntu.view.OrderDetailViewData;
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

    /**
     * <pre>
     *     使用用户id和订单的状态来查询订单的详细信息
     * </pre>
     * @param uid 用户id
     * @param status 订单状态
     * */
    @Override
    public List<OrderDetailViewData> selectOrderProductByUidStatus(Integer uid, Integer status) {
        List<OrderDetailViewData> orderDetailViewData = orderProductMapper.selectOrderProductByUidStatus(uid, status);
        return orderDetailViewData;
    }

    /**
     * @param id 订单id
     * */
    @Override
    public List<OrderDetailViewData> selectOrderProductById(Integer id) {
        return orderProductMapper.selectOrderProductById(id);
    }

}
