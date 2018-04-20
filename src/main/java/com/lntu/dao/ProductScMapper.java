package com.lntu.dao;

import com.lntu.entity.ProductSc;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductScMapper {
    //插入商品收藏记录
    Integer insert(ProductSc productSc);
    //按照用户查找收藏的商品
    ProductSc selectByUidPid(@Param(value = "uid") String uid, @Param(value = "pid") Integer pid);
    //修改用户收藏状态
    Integer updateByUidPid(@Param(value = "uid") String uid,@Param(value = "pid") Integer pid,@Param(value = "status") Integer status);
    //按uid查找收藏
    List<ProductSc> selectByUid(@Param(value = "uid")String uid);
}