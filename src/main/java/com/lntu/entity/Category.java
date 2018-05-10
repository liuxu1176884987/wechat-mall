package com.lntu.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Category {

    private Integer id;

    private Integer tid;

    private String name;

    private Integer sort;

    private Date addtime;

    private String concent;

    private String bz1;

    private String bz2;

    private String bz3;

    private Byte bz4;

    private String bz5;

}