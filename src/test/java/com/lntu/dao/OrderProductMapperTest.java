package com.lntu.dao;

import com.lntu.entity.OrderProduct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderProductMapperTest {

    @Autowired
    private OrderProductMapper orderProductMapper;

    @Test
    public void insertOrderProduct() {
        List<OrderProduct> list = new ArrayList<>();
        OrderProduct o1 = new OrderProduct();

        o1.setPid(111);
        o1.setPaySn("2222222222222220");
        o1.setOrderId(50);
        o1.setName("好吃的");
        o1.setPrice(new BigDecimal(500));
        o1.setPhotoX("c://wwwwwwwwwwwwww");
        o1.setProBuff("属性");
        o1.setAddtime(new Date());
        o1.setNum(66);
        o1.setProGuige("haha");

        OrderProduct o2 = new OrderProduct();
        o2.setPid(111);
        o2.setPaySn("3333333333333");
        o2.setOrderId(50);
        o2.setName("好吃的");
        o2.setPrice(new BigDecimal(500));
        o2.setPhotoX("c://wwwwwwwwwwwwww");
        o2.setProBuff("属性");
        o2.setAddtime(new Date());
        o2.setNum(66);
        o2.setProGuige("haha");

        list.add(o1);
        list.add(o2);

        orderProductMapper.insertOrderProduct(list);
    }
}