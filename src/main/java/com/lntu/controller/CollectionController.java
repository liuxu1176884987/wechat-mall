package com.lntu.controller;

import com.lntu.entity.ProductSc;
import com.lntu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 收藏处理
 * Created by lx-pc on 2018/4/17.
 */
@RestController
@RequestMapping(value = "collection")
public class CollectionController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "GetCollectCategoryList")
    @ResponseStatus(value = HttpStatus.OK)
    public Map<String,Object> getCollectionList(@RequestParam(value = "uid")String uid){
        List<ProductSc> productScList = productService.selectByUid(uid);

        return null;
    }

}
