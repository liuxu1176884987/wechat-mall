package com.lntu.entity;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class UserVoucher {

    private Integer id;

    private String uid;

    private Integer vid;

    private Integer shopId;

    private BigDecimal fullMoney;

    private BigDecimal amount;

    private Date startTime;

    private Date endTime;

    private Date addtime;

    private Byte status;

}