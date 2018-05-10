package com.lntu.dao;


import com.lntu.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper {
    // 按id查询分类
    List<Category> selectCategoryByTid(@Param(value = "tid")Integer tid);
}