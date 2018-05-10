package com.lntu.dao;

import com.lntu.entity.Product;
import com.lntu.entity.ProductWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    //查询所有商品
    List<Product> selectAll();
    //按照id查找商品
    ProductWithBLOBs selectById(Integer id);
    // 按ids查询商品
    List<Product> selectByIds(List ids);
    // 按分类查找商品
    List<Product> selectProductByCid(@Param(value = "cid")Integer cid);
}