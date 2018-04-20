package com.lntu.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class User {

    private Integer id;

    private String name;

    private String nickName;

    private String pwd;

    private Float jifen;

    private String photo;

    private String tel;

    private String qqId;

    private String email;

    private Byte sex;

    private Byte del;

    private String openid;

    private String source;

    private Date addtime;

    public User() {
    }
}