package com.lntu.view;

import com.lntu.entity.Product;
import lombok.Data;

/**
 * 商品详情
 * Created by lx-pc on 2018/4/9.
 */
@Data
public class ProductDetailViewData{
    // 商品详细信息
    private Product pro;
    // 商品主页轮播图
    private String[] imgArr;
    // 商品详情信息
    private String content;
    // 商品属性名
    private String[] commodityAttr;
    // 商品属性值
    private String[] attrValueList;
    //请求失败返回的错误信息
    private String err;

}