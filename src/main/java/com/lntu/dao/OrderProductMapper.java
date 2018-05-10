package com.lntu.dao;


import com.lntu.entity.OrderProduct;
import com.lntu.view.OrderDetailViewData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderProductMapper {
    // 插入订单商品
    Integer insertOrderProduct(@Param(value = "orderProductList") List<OrderProduct> orderProductList);
    // 按状态查询订单
    List<OrderDetailViewData> selectOrderProductByUidStatus(@Param(value = "uid")Integer uid, @Param(value = "status")Integer status);
    // 按id查找订单中的商品信息
    List<OrderDetailViewData> selectOrderProductById(@Param(value = "id")Integer id);
}