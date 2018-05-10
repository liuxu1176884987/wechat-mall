package com.lntu.controller;

import com.lntu.common.JsonData;
import com.lntu.entity.Category;
import com.lntu.enums.MallStatusEnum;
import com.lntu.exception.MallException;
import com.lntu.service.CategoryService;
import com.lntu.utils.LoginCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/Api/Category")
public class CategoryController {

    // 商品分类服务
    @Autowired
    private CategoryService categoryService;

    // 登陆校验
    @Autowired
    private LoginCheckUtil loginCheckUtil;

    /**
     * <pre>
     *     分类左侧的按钮
     * </pre>
     * */
    @PostMapping(value = "index")
    public JsonData index(@RequestParam(value = "sessionKey")String sessionKey){
        Boolean check = loginCheckUtil.check(sessionKey);
        // 返回的数据集合
        Map<String,Object> resultData = new HashMap<>();
        if(check){
            // 分类左侧菜单列表
            List<Category> leftMenuList = categoryService.selectCategoryByTid(1);
            if(leftMenuList != null){
                List<Category> catList = categoryService.selectCategoryByTid(leftMenuList.get(0).getId());
                resultData.put("catList",catList);
            }
            resultData.put("list",leftMenuList);
            return JsonData.success(1,"查询列表数据成功",resultData);
        }
        throw new MallException(MallStatusEnum.CLIENT_CHECK_FAIL);
    }

    /**
     * <pre>
     *     按id来获取分类列表
     * </pre>
     * @param catId 分类id
     * @return catList 分类列表
     * */
    @PostMapping(value = "getcat")
    public JsonData getCat(@RequestParam(value = "cat_id")Integer catId,
                           @RequestParam(value = "sessionKey")String sessionKey){
        Boolean check = loginCheckUtil.check(sessionKey);
        Map<String,Object> resultData = new HashMap<>();
        if(check){
            List<Category> catList = categoryService.selectCategoryByTid(catId);
            resultData.put("catList",catList);
            return JsonData.success(1,"获取分类列表成功",resultData);
        }
        throw new MallException(MallStatusEnum.CLIENT_CHECK_FAIL);
    }

}