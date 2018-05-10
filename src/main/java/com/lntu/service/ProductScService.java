package com.lntu.service;

import com.lntu.view.ProductViewData;

import java.util.List;

public interface ProductScService {
    // 查询出所有商品
    List<ProductViewData> selectProductScByUid(Integer pageindex,Integer pagesize,Integer uid);
    // 修改商品收藏状态
    Integer updateProductScStatus(Integer uid,Integer scId,Integer status);
}
