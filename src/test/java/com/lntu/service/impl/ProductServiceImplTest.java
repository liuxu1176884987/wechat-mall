package com.lntu.service.impl;

import com.lntu.entity.Product;
import com.lntu.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by lx-pc on 2018/4/3.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Test
    public void selectAll() throws Exception {
        List<Product> products = productService.selectAll(0,20);
        System.out.println(products);
    }

}