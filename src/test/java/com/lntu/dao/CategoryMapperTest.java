package com.lntu.dao;

import com.lntu.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryMapperTest {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void selectCategoryByTid() {
        List<Category> categories = categoryMapper.selectCategoryByTid(0);
        System.out.println(categories);
    }

}