package com.lntu.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Order {

    private Integer id;

    private String orderSn;

    private String paySn;

    private Integer shopId;

    private Integer uid;

    private BigDecimal price;

    private BigDecimal amount;

    private Date addtime;

    private Byte del;

    private String type;

    private BigDecimal priceH;

    private Byte status;

    private Integer vid;

    private String receiver;

    private String tel;

    private String addressXq;

    private Integer code;

    private Integer post;

    private String remark;

    private String postRemark;

    private Integer productNum;

    private String tradeNo;

    private String kuaidiName;

    private String kuaidiNum;

    private String back;

    private String backRemark;

    private Integer backAddtime;

    private Byte orderType;

}