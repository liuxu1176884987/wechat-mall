package com.lntu.view;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 购物车视图数据
 * Created by Administrator on 2018/4/13.
 */
@Data
public class ShoppingCarViewData {

    // 购物车id
    private Integer id;
    // 商品名字
    private String proName;
    // 是否选中
    private Boolean selected;
    // 商品图片
    private String photoX;
    // 商品价格
    private BigDecimal price;
    // 商品数量
    private Integer num;

    public ShoppingCarViewData() {
    }

    public ShoppingCarViewData(Integer id, String proName, Boolean selected, String photoX, String proName1, BigDecimal price, Integer num) {
        this.id = id;
        this.proName = proName;
        this.selected = selected;
        this.photoX = photoX;
        this.proName = proName1;
        this.price = price;
        this.num = num;
    }

}
