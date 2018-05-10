package com.lntu.service;

import com.lntu.entity.OrderProduct;
import com.lntu.view.OrderDetailViewData;

import java.util.List;

public interface OrderProductService {
    // 插入订单中的商品
    Integer insertOrderProduct(List<OrderProduct> orderProductList);
    // 按用户id和status来查询数据
    List<OrderDetailViewData> selectOrderProductByUidStatus(Integer uid,Integer status);
    // 按id查询订单中包含的商品信息
    List<OrderDetailViewData> selectOrderProductById(Integer id);
}
