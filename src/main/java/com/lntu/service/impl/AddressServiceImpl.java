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
    public List<Address> selectAddressByUid(Integer uid) {
        return addressMapper.selectByUid(uid);
    }

    @Override
    public Integer updateDefaultAddress(Integer uid,Integer id) {
        // 把用户的所有地址设置为不是默认
        addressMapper.updateByUid(0,uid);
        // 把指定id的地址设为默认
        return addressMapper.updateByUidId(1,uid,id);
    }

    @Override
    @Transactional
    public Integer deleteByUidId(Integer uid, Integer id) {
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

    // 按区域id来查找区域代码
    @Override
    public String selectAreaById(Integer id) {
        ChinaCity chinaCity = chinaCityMapper.selectAreaById(id);
        if(chinaCity != null){
            return chinaCity.getCode();
        }
        return null;
    }

    // 添加收货地址
    @Override
    @Transactional
    public Integer addAddress(Address address) {
        return addressMapper.addAddress(address);
    }

    // 按ids查询省市区
    @Override
    public String selectByProvinceCityAreaById(List ids) {
        List<ChinaCity> chinaCityList = chinaCityMapper.selectProvinceCityAreaById(ids);
        if(chinaCityList != null){
            StringBuilder sb = new StringBuilder();
            chinaCityList.forEach(chinaCity -> {
                sb.append(chinaCity.getName()+" ");
            });
            String addressStr = sb.toString().trim();
            return addressStr;
        }
        return null;
    }

    /**
     * @param uid 传一个用户id
     * @return address 返回默认的用户地址
     * */
    @Override
    public Address selectDefaultByUid(Integer uid) {
        Address address = addressMapper.selectDefaultAddressByUid(uid);
        return address;
    }

    /**
     * @param id 地址的id
     * */
    @Override
    public Address selectById(Integer id) {
        return addressMapper.selectById(id);
    }

}