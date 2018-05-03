package com.lntu.dao;

import com.lntu.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressMapper {
    // 按uid查询地址
    List<Address> selectByUid(@Param(value = "uid")Integer uid);
    // 按uid修改默认地址
    Integer updateByUid(@Param(value = "isDefault")Integer isDefault,@Param(value = "uid")Integer uid);
    // 按id修改默认地址
    Integer updateByUidId(@Param(value = "isDefault")Integer isDefault,@Param(value = "uid")Integer uid,@Param(value = "id")Integer id);
    // 按uid和id来删除地址
    Integer deleteByUidId(@Param(value = "uid")Integer uid,@Param(value = "id")Integer id);
    // 增加数据
    Integer addAddress(Address address);
    // 查询用户默认地址
    Address selectDefaultAddressByUid(@Param(value = "uid") Integer uid);
    // 按id查询收货地址
    Address selectById(@Param(value = "id") Integer id);
}