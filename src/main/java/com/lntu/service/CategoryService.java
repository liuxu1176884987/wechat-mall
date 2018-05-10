package com.lntu.service;

import com.lntu.entity.Category;

import java.util.List;

public interface CategoryService {
    // 按父id查询分类
    List<Category> selectCategoryByTid(Integer tid);
}