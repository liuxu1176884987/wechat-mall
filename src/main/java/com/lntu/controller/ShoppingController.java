package com.lntu.controller;

import com.lntu.common.JsonData;
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
import org.springframework.data.redis.core.StringRedisTemplate;
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

    // Redis服务
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // sessionKey前缀
    @Value("${LOGIN.SESSION_KEY_PRE}")
    private String SESSION_KEY_PRE;


    /**
     * 添加商品到购物车
     * @param userId 用户id
     * @param productId 商品id
     * @param buynum 购买数量
     * @param sessionKey 验证参数
     * */
    @PostMapping(value = "add")
    @ResponseStatus(value = HttpStatus.OK)
    public JsonData add(@RequestParam(value = "uid")Integer userId,
                        @RequestParam(value = "pid")Integer productId,
                        @RequestParam(value = "num")Integer buynum,
                        @RequestParam(value = "sessionKey")String sessionKey){
        String checkStr = stringRedisTemplate.opsForValue().get(SESSION_KEY_PRE + ":" + sessionKey);
        if("login success".equals(checkStr)){
            // 1.验证成功
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
                return JsonData.success(1,"添加购物车成功",cid);
            }
            return JsonData.fail(500,"不存在此商品");
        }
        return JsonData.fail(402,"验证失败");
    }


    // 显示购物车主页数据
    @PostMapping(value = "index")
    public JsonData index(@RequestParam(value = "user_id")Integer userId,
                     @RequestParam(value = "sessionKey")String sessionKey){

        String checkStr = stringRedisTemplate.opsForValue().get(SESSION_KEY_PRE+":"+sessionKey);

        if("login success".equals(checkStr)){
            // 验证成功
            List<ShoppingCar> shoppingCars = shoppingCarService.shoppingCarList(userId);

            if(shoppingCars != null){
                return JsonData.success(1,"返回商品数据",shoppingCars);
            }else {
                return JsonData.success(1,"商品为空","");
            }
        }
        return JsonData.fail(500,"购物车查询失败");
    }


    // 修改购物车商品数量
    @PostMapping(value = "up_cart")
    @ResponseStatus(value = HttpStatus.OK)
    public Map upCart(@RequestParam(value = "user_id")Integer userId,
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
    public JsonData delete(@RequestParam(value = "cart_id")Integer cartId,
                      @RequestParam(value = "sessionKey")String sessionKey){

        String checkStr = stringRedisTemplate.opsForValue().get(SESSION_KEY_PRE + ":" + sessionKey);

        // 验证登录是否有效
        if("login success".equals(checkStr)){
            Integer result = shoppingCarService.deleteByCartId(cartId);
            return JsonData.success(1,"查询成功",null);
        }
        throw new MallException(MallStatusEnum.CLIENT_CHECK_FAIL);
    }

}