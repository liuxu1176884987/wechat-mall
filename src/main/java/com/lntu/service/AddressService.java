package com.lntu.service;

import com.lntu.entity.Address;
import com.lntu.entity.ChinaCity;

import java.util.List;

/**
 * 地址服务
 * Created by Administrator on 2018/4/18.
 */
public interface AddressService {
    // 查询所有城市
    List<ChinaCity> selectAllProvince();
    // 按uid查询地址
    List<Address> selectAddressByUid(String uid);
    // 修改默认地址
    Integer updateDefaultAddress(String uid,Integer id);
    // 删除用户地址
    Integer deleteByUidId(String uid,Integer id);
    // 按省的id来查询城市列表
    List<ChinaCity> selectCityByProvinceId(Integer provinceId);
    // 按城市来查找区域列表
    List<ChinaCity> selectAreaByCityId(Integer cid);
}