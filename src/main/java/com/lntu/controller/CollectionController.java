package com.lntu.controller;

import com.lntu.common.JsonData;
import com.lntu.entity.Product;
import com.lntu.enums.MallStatusEnum;
import com.lntu.exception.MallException;
import com.lntu.service.ProductScService;
import com.lntu.service.ProductService;
import com.lntu.utils.LoginCheckUtil;
import com.lntu.view.ProductViewData;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 收藏处理
 * Created by lx-pc on 2018/4/17.
 */
@RestController
@RequestMapping(value = "API/collection")
public class CollectionController {

    // 商品收藏服务
    @Autowired
    private ProductScService productScService;

    // 商品服务
    @Autowired
    private ProductService productService;

    // 用户校验服务
    @Autowired
    private LoginCheckUtil loginCheckUtil;


    /**
     * <tag>
     *     获取收藏商品列表
     * </tag>
     * @param uid 用户id
     * */
    @PostMapping(value = "GetCollectCategoryList")
    @ResponseStatus(value = HttpStatus.OK)
    public JsonData getCollectionList(@RequestParam(value = "userId")Integer uid,
                                      @RequestParam(value = "sessionKey")String sessionKey,
                                      @RequestParam(value = "pageindex")Integer pageindex,
                                      @Param(value = "pagesize")Integer pagesize){

        Boolean check = loginCheckUtil.check(sessionKey);
        if(check){
            List<ProductViewData> productViewData = productScService.selectProductScByUid(pageindex, pagesize, uid);

            return JsonData.success(1,"查询商品收藏列表成功",productViewData);
        }
        throw new MallException(MallStatusEnum.CLIENT_CHECK_FAIL);
    }

    /**
     *
     * */
    @PostMapping(value = "RemoveCollectCategory")
    @ResponseStatus(value = HttpStatus.OK)
    public JsonData removeCollectCategory(@RequestParam(value = "uid")Integer uid,
                                          @RequestParam(value = "scId")Integer scId,
                                          @RequestParam(value = "sessionKey")String sessionKey){
        Boolean check = loginCheckUtil.check(sessionKey);
        if(check){
            productScService.updateProductScStatus(uid,scId,0);
            return JsonData.success(1,"取消收藏成功",null);
        }
        throw new MallException(MallStatusEnum.CLIENT_CHECK_FAIL);
    }

}