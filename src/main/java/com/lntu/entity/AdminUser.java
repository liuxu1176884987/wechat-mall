package com.lntu.entity;

import lombok.Data;

import java.util.Date;

@Data
public class AdminUser {

    private Integer id;

    private String name;

    private String nickName;

    private String pwd;

    private Byte qx;

    private Date addtime;

    private Byte del;

    public AdminUser() {
    }

}