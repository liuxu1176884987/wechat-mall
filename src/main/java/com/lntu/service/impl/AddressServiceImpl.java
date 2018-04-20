package com.lntu.service.impl;

import com.lntu.dao.AddressMapper;
import com.lntu.dao.ChinaCityMapper;
import com.lntu.entity.Address;
import com.lntu.entity.ChinaCity;
import com.lntu.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 地址管理服务
 * Created by Administrator on 2018/4/18.
 */
@Service
public class AddressServiceImpl implements AddressService {

    // 中国所有地区服务
    @Autowired
    private ChinaCityMapper chinaCityMapper;

    // 用户地址服务
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<ChinaCity> selectAllProvince() {
        return chinaCityMapper.selectAllProvince();
    }

    @Override
    public List<Address> selectAddressByUid(String uid) {
        return addressMapper.selectByUid(uid);
    }

    @Override
    public Integer updateDefaultAddress(String uid,Integer id) {
        // 把用户的所有地址设置为不是默认
        addressMapper.updateByUid(0,uid);
        // 把指定id的地址设为默认
        return addressMapper.updateByUidId(1,uid,id);
    }

    @Override
    @Transactional
    public Integer deleteByUidId(String uid, Integer id) {
        return addressMapper.deleteByUidId(uid,id);
    }

    @Override
    public List<ChinaCity> selectCityByProvinceId(Integer provinceId) {
        List<ChinaCity> chinaCities = chinaCityMapper.selectCityByProvinceId(provinceId);
        return chinaCities;
    }

    // 按照城市来查找区域信息
    @Override
    @Transactional
    public List<ChinaCity> selectAreaByCityId(Integer cid) {
        List<ChinaCity> chinaCities = chinaCityMapper.selectByCityId(cid);
        return chinaCities;
    }

}
