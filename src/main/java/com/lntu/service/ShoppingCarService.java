package com.lntu.service;

import com.lntu.entity.ShoppingCar;
import com.lntu.view.ShoppingCarViewData;

import java.util.List;

/**
 * 购物车服务
 * Created by lx-pc on 2018/4/10.
 */
public interface ShoppingCarService {
    // 添加到购物车
    Integer add(ShoppingCar shoppingCar);
    // 显示购物车所有商品
    List<ShoppingCar> shoppingCarList(String uid);
    // 按照uid和id来修改商品数量
    Integer updateByUidId(String uid,Integer id,Integer num);
    // 按照cartId来删除购物车信息
    Integer deleteByCartId(Integer cid);
    // 按照ids来查询信息
    List<ShoppingCarViewData> productViewDataByIds(List ids);
}