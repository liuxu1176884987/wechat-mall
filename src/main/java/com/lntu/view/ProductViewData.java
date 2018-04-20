package com.lntu.view;

import lombok.Data;

/**
 * 商品属性数据
 * Created by lx-pc on 2018/4/3.
 */
@Data
public class ProductViewData {
    //商品id
    private Integer id;
    //商品图片
    private String photoX;
    //商品名字
    private String name;
    //商品优惠价格
    private Integer priceYh;
    //商品原价格
    private Integer price;
    //是否是新品
    private Integer isShow;
    //是否热销
    private Integer isHot;
    //销量
    private Integer shiyong;

    public ProductViewData(Integer id, String photoX, String name, Integer priceYh, Integer price, Integer isShow, Integer isHot, Integer shiyong) {
        this.id = id;
        this.photoX = photoX;
        this.name = name;
        this.priceYh = priceYh;
        this.price = price;
        this.isShow = isShow;
        this.isHot = isHot;
        this.shiyong = shiyong;
    }

    public ProductViewData() {
    }

}