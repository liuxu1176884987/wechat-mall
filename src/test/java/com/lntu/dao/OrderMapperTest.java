package com.lntu.dao;

import com.lntu.view.OrderDetailViewData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderProductMapper orderProductMapper;

    @Test
    public void insertOrder() {
    }

    @Test
    public void updateOrderById() {
    }

    @Test
    public void countOrderStatus() {
        Integer count = orderMapper.countOrderStatus(10, 10);
        System.out.println(count);
    }

}