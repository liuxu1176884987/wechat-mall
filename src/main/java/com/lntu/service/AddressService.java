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
    List<Address> selectAddressByUid(Integer uid);
    // 修改默认地址
    Integer updateDefaultAddress(Integer uid,Integer id);
    // 删除用户地址
    Integer deleteByUidId(Integer uid,Integer id);
    // 按省的id来查询城市列表
    List<ChinaCity> selectCityByProvinceId(Integer provinceId);
    // 按城市来查找区域列表
    List<ChinaCity> selectAreaByCityId(Integer cid);
    // 按照区域id来查找区域
    String selectAreaById(Integer id);
    // 添加收货地址
    Integer addAddress(Address address);
    // 按ids查询省市区
    String selectByProvinceCityAreaById(List ids);
    // 查询用户的默认地址
    Address selectDefaultByUid(Integer uid);
    // 按id来查询地址
    Address selectById(Integer id);
}