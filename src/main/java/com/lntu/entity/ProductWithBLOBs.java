package com.lntu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductWithBLOBs extends Product {
    // 图片组
    @JsonIgnore
    private String photoString;
    // 商品详情
    @JsonIgnore
    private String content;
}