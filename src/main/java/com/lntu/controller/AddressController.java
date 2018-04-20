package com.lntu.controller;

import com.lntu.entity.Address;
import com.lntu.entity.ChinaCity;
import com.lntu.service.AddressService;
import org.apache.commons.collections.map.HashedMap;
import org.aspectj.lang.annotation.DeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 地址管理
 * Created by Administrator on 2018/4/18.
 */
@RestController
@RequestMapping(value = "/Api/Address")
public class AddressController {

    // 地址服务
    @Autowired
    private AddressService addressService;

    // 获取中国所有省
    @PostMapping(value = "get_province")
    @ResponseStatus(value = HttpStatus.OK)
    public Map getProvince(){
        // 1.查询出所有省
        List<ChinaCity> chinaCityList = addressService.selectAllProvince();
        Map<String,Object> resultData = new HashedMap();
        resultData.put("status",1);
        resultData.put("list",chinaCityList);
        return resultData;
    }

    // 获取对应省的城市
    @PostMapping(value = "get_city")
    @ResponseStatus(value = HttpStatus.OK)
    public Map getCity(@RequestParam(value = "sheng")Integer provinceId){
        // 1.按照省的id查询出所有城市信息
        List<ChinaCity> cityList = addressService.selectCityByProvinceId(provinceId);
        // 2.返回所有数据
        Map<String,Object> resultData = new HashedMap();
        if(resultData != null){
            resultData.put("status",1);
            resultData.put("city_list",cityList);
            resultData.put("sheng",provinceId);
        }else {
            resultData.put("status",500);
        }
        return resultData;
    }

    // 获取对应的区域
    @PostMapping(value = "get_area")
    @ResponseStatus(value = HttpStatus.OK)
    public Map getArea(@RequestParam(value = "city")Integer cityId){
        List<ChinaCity> cityList = addressService.selectAreaByCityId(cityId);
        Map<String,Object> resultData = new HashedMap();
        if(cityList != null){
            resultData.put("status",1);
            resultData.put("area_list",cityList);
            resultData.put("city",cityId);
        }else {
            resultData.put("status",500);
        }
        return resultData;
    }

    // 获取用户地址
    @PostMapping(value = "index")
    @ResponseStatus(value = HttpStatus.OK)
    public Map index(@RequestParam(value = "user_id")String uid){
        List<Address> addresses = addressService.selectAddressByUid(uid);
        Map<String,Object> resultData = new HashedMap();
        resultData.put("adds",addresses);
        return resultData;
    }

    // 设置默认收货地址
    @PostMapping(value = "set_default")
    @ResponseStatus(value = HttpStatus.OK)
    public Map setDefault(@RequestParam(value = "uid")String uid,
                          @RequestParam(value = "addr_id")Integer addrId,
                          @RequestParam(value = "sessionKey")String sessionKey,
                          HttpServletRequest request){
        // 1.设置默认地址
        Integer result = addressService.updateDefaultAddress(uid, addrId);
        // 2.设置返回数据
        Map<String,Object> resultData = new HashedMap();
        if(result > 0){
            resultData.put("status",1);
        }else {
            resultData.put("status",0);
        }
        return resultData;
    }

    // 删除收货地址
    @PostMapping(value = "del_adds")
    @ResponseStatus(value = HttpStatus.OK)
    @DeclareAnnotation(value = "login")
    public Map delAdds(@RequestParam(value = "id_arr")Integer addrId,
                       @RequestParam(value = "sessionKey")String sessionKey,
                       @RequestParam(value = "user_id")String uid){
        Integer result = addressService.deleteByUidId(uid, addrId);
        Map<String,Object> resultData = new HashedMap();
        if(result > 0){
            resultData.put("status",1);
        }else {
            resultData.put("status",500);
        }
        return resultData;
    }

}