package com.lntu.entity;

import lombok.Data;

@Data
public class ProductSc {

    private Integer id;

    private Integer pid;

    private String uid;

    //收藏的状态   0:删除   1:正常
    private Byte status;

    public ProductSc(Integer pid, String uid, Byte status) {
        this.pid = pid;
        this.uid = uid;
        this.status = status;
    }

    public ProductSc() {
    }

}