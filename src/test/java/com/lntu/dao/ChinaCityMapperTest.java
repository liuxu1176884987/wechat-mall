package com.lntu.dao;

import com.lntu.entity.ChinaCity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ChinaCityMapperTest {

    @Autowired
    private ChinaCityMapper chinaCityMapper;

    @Test
    public void selectAllProvince() throws Exception {
        List<ChinaCity> chinaCityList = chinaCityMapper.selectAllProvince();
        System.out.println(chinaCityList);
    }

    @Test
    public void selectProvinceCityAreaById(){
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(6);
        List<ChinaCity> chinaCitieList = chinaCityMapper.selectProvinceCityAreaById(list);
        System.out.println(chinaCitieList);
    }

}