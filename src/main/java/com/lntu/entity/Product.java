package com.lntu.entity;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class Product implements Comparable<Product>{
    // 产品id
    private Integer id;
    // 商铺id
    private Integer shopId;
    // 品牌id
    private Integer brandId;
    // 产品名称
    private String name;
    // 广告语
    private String intro;
    // 产品编号
    private String proNumber;
    // 价格
    private BigDecimal price;
    // 优惠价格
    private BigDecimal priceYh;
    // 赠送积分
    private Integer priceJf;
    // 小图
    private String photoX;
    // 大图
    private String photoD;
    // 添加时间
    private Integer addtime;
    // 修改时间
    private Integer updatetime;
    // 排序
    private Integer sort;
    // 人气
    private Integer renqi;
    // 销量
    private Integer shiyong;
    // 数量
    private Integer num;
    // 是否推荐  1:推荐  0:不推荐
    private Byte type;
    // 删除状态
    private Byte del;
    // 删除时间
    private Integer delTime;
    // 产品属性
    private String proBuff;
    // 分类id
    private Integer cid;
    // 单位
    private String company;
    // 是否新品
    private Boolean isShow;
    // 是否下架
    private Boolean isDown;
    // 是否热卖
    private Boolean isHot;
    // 是否折扣
    private Boolean isSale;
    // 抢购开始时间
    private Integer startTime;
    // 抢购结束时间
    private Integer endTime;
    // 产品类型   1.普通    2.抢购产品
    private Boolean proType;

    public Product() {
    }

    @Override
    public int compareTo(Product product) {
        if(product != null){
            return product.getSort() - this.sort;
        }
        return 0;
    }

}