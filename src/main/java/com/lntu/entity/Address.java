package com.lntu.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Address {

    private Integer id;

    private String name;

    private String tel;

    private Integer sheng;

    private Integer city;

    private Integer quyu;

    private String address;

    private String addressXq;

    private Integer code;

    private Integer uid;

    private Byte isDefault;

}