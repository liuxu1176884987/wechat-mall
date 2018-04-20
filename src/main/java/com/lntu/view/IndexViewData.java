package com.lntu.view;

import lombok.Data;

import java.util.List;

/**
 * 返回主页需要的数据格式
 * Created by lx-pc on 2018/4/3.
 */
@Data
public class IndexViewData {
    //主页大广告位
    private List<String> ggtop;
    //未知属性
    private String procat;
    //返回商品信息
    private List<ProductViewData> prolist;
    //未知属性
    private String brand;
    //未知属性
    private String course;
    //错误提示
    private String err;
}
