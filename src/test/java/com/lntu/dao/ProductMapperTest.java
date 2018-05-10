package com.lntu.dao;

import com.lntu.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/3.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductMapperTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void deleteByPrimaryKey() throws Exception {

    }

    @Test
    public void insert() throws Exception {

    }

    @Test
    public void insertSelective() throws Exception {

    }

    @Test
    public void selectByPrimaryKey() throws Exception {
        List<Product> products = productMapper.selectAll();
        System.out.println(products);
    }

    @Test
    public void updateByPrimaryKeySelective() throws Exception {

    }

    @Test
    public void updateByPrimaryKeyWithBLOBs() throws Exception {

    }

    @Test
    public void updateByPrimaryKey() throws Exception {

    }

    @Test
    public void selectAll(){
        productMapper.selectAll();
    }

    @Test
    public void selectById(){
        Product product = productMapper.selectById(284);
        System.out.println(product);
    }

    @Test
    public void selectByIds(){
        List<Integer> list = new ArrayList<>();
        list.add(264);
        list.add(265);
        List<Product> products = productMapper.selectByIds(list);
        System.out.println(products);
    }

    @Test
    public void selectProductByCid(){
        List<Product> products = productMapper.selectProductByCid(15);
        System.out.println(products);
    }

}