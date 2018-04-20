package com.lntu.service.impl;

import com.lntu.service.AddressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lx-pc on 2018/4/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceImplTest {

    @Autowired
    private AddressService addressService;

    @Test
    public void selectAllProvince() throws Exception {

    }

    @Test
    public void selectAddressByUid() throws Exception {

    }

    @Test
    public void updateDefaultAddress() throws Exception {

    }

    @Test
    public void deleteByUidId() throws Exception {

    }

    @Test
    public void selectCityByProvinceId() throws Exception {

    }

    @Test
    public void selectAreaByCityId() throws Exception {

    }

    @Test
    public void selectAreaById() throws Exception {

    }

    @Test
    public void addAddress() throws Exception {

    }

    @Test
    public void selectByProvinceCityAreaById() throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(6);
        String s = addressService.selectByProvinceCityAreaById(list);
        System.out.println(s);
    }

}