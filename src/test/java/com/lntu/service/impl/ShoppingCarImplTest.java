package com.lntu.service.impl;

import com.lntu.entity.ShoppingCar;
import com.lntu.service.ShoppingCarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingCarImplTest {

    @Autowired
    private ShoppingCarService shoppingCarService;

    @Test
    public void add() {
    }

    @Test
    public void shoppingCarList() {
    }

    @Test
    public void updateByUidId() {
    }

    @Test
    public void deleteByCartId() {
    }

    @Test
    public void productViewDataByIds() {
    }

    @Test
    public void computeShoppingPrice() {
        List<Integer> ids = new ArrayList();
        ids.add(29);
        ids.add(30);
        BigDecimal totalPrice = shoppingCarService.computeShoppingPrice(ids);
        System.out.println(totalPrice);
    }
}