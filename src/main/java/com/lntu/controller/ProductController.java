package com.lntu.controller;

import com.lntu.entity.ProductWithBLOBs;
import com.lntu.enums.MallStatusEnum;
import com.lntu.exception.MallException;
import com.lntu.service.ProductService;
import com.lntu.utils.WechatRequestCheck;
import com.lntu.view.ProductDetailViewData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 商品详情
 * Created by lx-pc on 2018/4/9.
 */
@RestController
@RequestMapping(value = "Api/Product")
public class ProductController {

    // 商品服务
    @Autowired
    private ProductService productService;

    @Value("${wechat-request-token}")
    private String token;

    @PostMapping(value = "index")
    @ResponseStatus(HttpStatus.OK)
    public ProductDetailViewData findById(@RequestParam(value = "pro_id")Integer pro_id){
        //1.查询商品所有信息
        ProductWithBLOBs product = productService.selectById(pro_id);
        ProductDetailViewData productDetailViewData = new ProductDetailViewData();
        // 2.设置商品属性
        productDetailViewData.setPro(product);
        // 3.设置商品图片
        String[] photoString = product.getPhotoString().trim().split(",");
        productDetailViewData.setImgArr(photoString);
        // 4.设置商品详细介绍
        productDetailViewData.setContent(product.getContent());
        // 5.设置返回的错误信息
        productDetailViewData.setErr("哎呦,商品没有找到");


        return productDetailViewData;
    }

    // 商品收藏
    @RequestMapping(value = "col")
    @ResponseStatus(value = HttpStatus.OK)
    public Integer productSc(@RequestParam(value = "uid")String uid,
                             @RequestParam(value = "pid")Integer pid,
                             HttpServletRequest request){
        //1.客户端请求验证
        if(WechatRequestCheck.check(request,token)){
            Integer result = productService.productSc(pid,uid);
            return result;
        }
        //2.客户端验证失败,抛出异常
        Map<String,Object> param = new HashMap<>();
        param.put("uid",uid);
        param.put("pid",pid);
        throw new MallException(MallStatusEnum.CLIENT_CHECK_FAIL,param);
    }

    // 查看当前用户是否收藏了商品
    @PostMapping(value = "initSc")
    public Integer initSc(@RequestParam(value = "uid")String uid,
                           @RequestParam(value = "pid")Integer pid){
        return productService.initSc(pid,uid);
    }

}