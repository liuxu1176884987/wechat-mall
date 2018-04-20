package com.lntu.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ChinaCity {

    private Integer id;

    private Integer tid;

    private String name;

    private String code;

    private String head;

    private Byte type;

    public ChinaCity() {
    }

}