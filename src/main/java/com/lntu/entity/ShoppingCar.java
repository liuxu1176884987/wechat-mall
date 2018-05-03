package com.lntu.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ShoppingCar {

    //购物车id
    private Integer id;
    //商品id
    private Integer pid;
    //商品单价
    private BigDecimal price;
    //商品优惠价格
    private BigDecimal priceYh;
    //数量
    private Integer num;
    //属性
    private String buff;
    //添加时间
    private Date addtime;
    //用户id
    private Integer uid;
    //商家id
    private Integer shopId;
    //规格id
    private Integer gid;
    //类型    0:热卖   1:团购   2:普通商品
    private Byte type;
    // 商品图片
    private String photoX;
}