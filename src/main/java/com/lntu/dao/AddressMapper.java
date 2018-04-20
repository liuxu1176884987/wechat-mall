package com.lntu.dao;

import com.lntu.entity.Address;
import com.lntu.entity.ChinaCity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressMapper {
    // 按uid查询地址
    List<Address> selectByUid(@Param(value = "uid")String uid);
    // 按uid修改默认地址
    Integer updateByUid(@Param(value = "isDefault")Integer isDefault,@Param(value = "uid")String uid);
    // 按id修改默认地址
    Integer updateByUidId(@Param(value = "isDefault")Integer isDefault,@Param(value = "uid")String uid,@Param(value = "id")Integer id);
    // 按uid和id来删除地址
    Integer deleteByUidId(@Param(value = "uid")String uid,@Param(value = "id")Integer id);

}