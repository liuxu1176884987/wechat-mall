package com.lntu.dao;

import com.lntu.entity.ShoppingCar;
import com.lntu.view.ShoppingCarViewData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShoppingCarMapper {
    // 添加购物车商品
    Integer add(ShoppingCar shoppingCar);
    // 按照用户查询所有商品
    List<ShoppingCar> selectShoppingByUid(@Param(value = "uid") Integer uid);
    // 修改购物车商品数量
    Integer updateByUidId(@Param(value = "uid")Integer uid,@Param(value = "id")Integer id,@Param(value = "num")Integer num);
    // 按照pid和uid查找商品
    ShoppingCar selectByUidPid(@Param(value = "uid") Integer uid,@Param(value = "pid") Integer pid);
    // 按照pid和uid修改商品
    Integer updateByUidPid(ShoppingCar shoppingCar);
    // 按照购物车id删除购物车商品
    Integer deleteByCartId(@Param(value = "cid")Integer cid);
    // 按照ids来查询出购物车中的商品
    List<ShoppingCarViewData> selectByIds(List list);
}