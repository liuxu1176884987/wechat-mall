package com.lntu.dao;

import com.lntu.entity.ProductSc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by lx-pc on 2018/4/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductScMapperTest {

    @Autowired
    private ProductScMapper productScMapper;

    @Test
    public void insert() throws Exception {
        ProductSc productSc = new ProductSc();
        productSc.setUid(10);
        productSc.setPid(2);
        productSc.setStatus(new Byte("1"));
        productScMapper.insert(productSc);
    }

    @Test
    public void selectByUidPid(){
        ProductSc productSc = productScMapper.selectByUidPid(10, 2);
        System.out.println(productSc);
    }

    @Test
    public void updateByUidPid(){
        productScMapper.updateByUidPid(10,2,2);
    }

}