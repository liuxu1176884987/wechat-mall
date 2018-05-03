package com.lntu.service;

import com.lntu.entity.OrderProduct;

import java.util.List;

public interface OrderProductService {
    Integer insertOrderProduct(List<OrderProduct> orderProductList);
}
