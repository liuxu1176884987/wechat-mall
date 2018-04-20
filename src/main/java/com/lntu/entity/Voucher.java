package com.lntu.entity;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class Voucher {

    private Integer id;

    private Integer shopId;

    private String title;

    private BigDecimal fullMoney;

    private BigDecimal amount;

    private Date startTime;

    private Date endTime;

    private Integer point;

    private Integer count;

    private Integer receiveNum;

    private Date addtime;

    private Boolean type;

    private Boolean del;

    private String proid;

}