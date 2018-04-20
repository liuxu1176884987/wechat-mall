package com.lntu.dao;

import com.lntu.entity.ChinaCity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChinaCityMapper {
    // 查询所有省
    List<ChinaCity> selectAllProvince();
    // 按省的编号来查找相应的城市
    List<ChinaCity> selectCityByProvinceId(@Param(value = "pid")Integer provinceId);
    // 按城市id来查找区域
    List<ChinaCity> selectByCityId(@Param(value = "cid")Integer cid);
}