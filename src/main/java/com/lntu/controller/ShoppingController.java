package com.lntu.controller;

import com.lntu.entity.ProductWithBLOBs;
import com.lntu.entity.ShoppingCar;
import com.lntu.enums.MallStatusEnum;
import com.lntu.exception.MallException;
import com.lntu.service.ProductService;
import com.lntu.service.ShoppingCarService;
import com.lntu.utils.WechatRequestCheck;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车相关操作
 * Created by Administrator on 2018/4/9.
 */
@RestController
@RequestMapping(value = "/Api/Shopping")
public class ShoppingController {

    // 购物车服务
    @Autowired
    private ShoppingCarService shoppingCarService;

    // 商品服务
    @Autowired
    private ProductService productService;

    // 获取加密token
    @Value("${wechat-request-token}")
    private String token;

    // 添加商品到购物车
    @PostMapping(value = "add")
    @ResponseStatus(value = HttpStatus.OK)
    public Map add(@RequestParam(value = "uid")String userId,
                      @RequestParam(value = "pid")Integer productId,
                      @RequestParam(value = "num")Integer buynum,
                      HttpServletRequest request){

        // 验证是否是微信小程序发来的请求
        if(WechatRequestCheck.check(request,token)){
            //返回信息
            Map<String,Object> resultParam = new HashedMap();

            // 1.按照id查询商品
            ProductWithBLOBs product = productService.selectById(productId);
            if(product != null){
                ShoppingCar shoppingCar = new ShoppingCar();
                shoppingCar.setPid(product.getId());
                shoppingCar.setPrice(product.getPriceYh());
                shoppingCar.setNum(buynum);
                shoppingCar.setBuff("");
                shoppingCar.setAddtime(new Date());
                shoppingCar.setUid(userId);
                shoppingCar.setShopId(0);
                // 返回添加购物车的id号
                Integer cid = shoppingCarService.add(shoppingCar);
                resultParam.put("cart_id",cid);
                resultParam.put("state",1);
                return resultParam;
            }
            resultParam.put("err","添加商品失败");
            return resultParam;
        }

        Map<String,Object> param = new HashedMap();
        param.put("uid",userId);
        param.put("pid",productId);
        param.put("num",buynum);
        throw new MallException(MallStatusEnum.ADD_SHOPPING_CAR_FAIL,param);
    }


    // 显示购物车主页数据
    @PostMapping(value = "index")
    public Map index(@RequestParam(value = "user_id")String userId){
        List<ShoppingCar> shoppingCars = shoppingCarService.shoppingCarList(userId);
        Map<String,Object> resultData = new HashedMap();
        if(shoppingCars != null && shoppingCars.size()>0){
            resultData.put("cart",shoppingCars);
        }else {
            resultData.put("cart","");
        }
        return resultData;
    }


    // 修改购物车商品数量
    @PostMapping(value = "up_cart")
    @ResponseStatus(value = HttpStatus.OK)
    public Map upCart(@RequestParam(value = "user_id")String userId,
                      @RequestParam(value = "num")Integer num,
                      @RequestParam(value = "cart_id")Integer cartId){
        shoppingCarService.updateByUidId(userId,cartId,num);
        List<ShoppingCar> shoppingCars = shoppingCarService.shoppingCarList(userId);
        Map<String,Object> resultData = new HashMap<>();
        if(num>=1){
            resultData.put("status",1);
            resultData.put("carts",shoppingCars);
        }else {
            resultData.put("status",0);
        }
        return resultData;
    }

    // 删除购物车商品
    @PostMapping(value = "delete")
    @ResponseStatus(value = HttpStatus.OK)
    public Map delete(@RequestParam(value = "cart_id")Integer cartId,
                      HttpServletRequest request){

        if(WechatRequestCheck.check(request,token)){
            Integer result = shoppingCarService.deleteByCartId(cartId);
            Map<String,Integer> resultData = new HashMap<>();
            if(result > 0){
                resultData.put("status",1);
            }else {
                resultData.put("status",0);
            }
            return resultData;
        }
        Map<String,Integer> params = new HashMap<>();
        params.put("cartId",cartId);
        throw new MallException(MallStatusEnum.CLIENT_CHECK_FAIL,params);
    }


}