package com.lntu.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderProduct {

    private Integer id;

    private Integer pid;

    private String paySn;

    private Integer orderId;

    private String name;

    private BigDecimal price;

    private String photoX;

    private String proBuff;

    private Date addtime;

    private Integer num;

    private String proGuige;

    public OrderProduct() {
    }

    public OrderProduct(Integer pid, String paySn, Integer orderId, String name, BigDecimal price, String photoX, String proBuff, Date addtime, Integer num, String proGuige) {
        this.pid = pid;
        this.paySn = paySn;
        this.orderId = orderId;
        this.name = name;
        this.price = price;
        this.photoX = photoX;
        this.proBuff = proBuff;
        this.addtime = addtime;
        this.num = num;
        this.proGuige = proGuige;
    }

}