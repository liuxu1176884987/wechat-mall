package com.lntu.service;

import com.lntu.entity.Product;
import com.lntu.entity.ProductSc;
import com.lntu.entity.ProductWithBLOBs;

import java.util.List;

/**
 * 商品服务
 * Created by lx-pc on 2018/4/3.
 */
public interface ProductService {
    // 查询所有商品
    List<Product> selectAll(Integer pageNum,Integer pageSize);
    // 按照id查询商品
    ProductWithBLOBs selectById(Integer id);
    // 按照pid和uid查询商品
    Integer productSc(Integer productId,Integer userId);
    // 初始化收藏
    Integer initSc(Integer pid,Integer uid);
    // 按照ids查找商品
    List<Product> selectByIds(List ids);
    // 按uid来查找收藏的商品
    List<ProductSc> selectByUid(Integer uid);
}
