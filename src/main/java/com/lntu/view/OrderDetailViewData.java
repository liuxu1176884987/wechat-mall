package com.lntu.view;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class OrderDetailViewData{
    private String photoX;
    private Integer pid;
    private String name;
    private BigDecimal priceYh;
    private Integer productNum;
    private BigDecimal proCount;
    private BigDecimal price;
    // 订单id
    private Integer id;
    // 订单编号
    private String orderSn;
}