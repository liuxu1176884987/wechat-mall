package com.lntu.dao;


import com.lntu.entity.OrderProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderProductMapper {
    Integer insertOrderProduct(@Param(value = "orderProductList") List<OrderProduct> orderProductList);
}