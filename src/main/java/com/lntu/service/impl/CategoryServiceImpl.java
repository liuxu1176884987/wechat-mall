package com.lntu.service.impl;

import com.github.pagehelper.PageHelper;
import com.lntu.dao.CategoryMapper;
import com.lntu.entity.Category;
import com.lntu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    // 商品分类服务
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * @param tid 父id
     * */
    @Override
    public List<Category> selectCategoryByTid(Integer tid) {
        return categoryMapper.selectCategoryByTid(tid);
    }

}
