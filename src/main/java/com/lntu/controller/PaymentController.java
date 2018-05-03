package com.lntu.controller;

import com.lntu.common.JsonData;
import com.lntu.entity.Address;
import com.lntu.entity.UserVoucher;
import com.lntu.enums.MallStatusEnum;
import com.lntu.exception.MallException;
import com.lntu.service.AddressService;
import com.lntu.service.ProductService;
import com.lntu.service.ShoppingCarService;
import com.lntu.service.UserVoucherService;
import com.lntu.utils.WechatRequestCheck;
import com.lntu.view.ShoppingCarViewData;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 付款接口
 * Created by Administrator on 2018/4/13.
 */
@RestController
@RequestMapping(value = "/Api/Payment")
public class PaymentController {

    // 商品服务
    @Autowired
    private ProductService productService;

    @Value("${wechat-request-token}")
    private String token;

    // 购物车服务
    @Autowired
    private ShoppingCarService shoppingCarService;

    // 优惠券服务
    @Autowired
    private UserVoucherService userVoucherService;

    // 地址服务
    @Autowired
    private AddressService addressService;

    // 商品总价格
    private BigDecimal total = BigDecimal.ZERO;

    // Redis服务
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // 商品最低运费
    @Value("${starting_price}")
    private Integer STARTING_PRICE;

    // sessionKey
    @Value("${LOGIN.SESSION_KEY_PRE}")
    private String SESSION_KEY_PRE;

    @PostMapping(value = "buy_cart")
    public JsonData buyCart(@RequestParam(value = "cart_id")String cartId,
                            @RequestParam(value = "uid")Integer uid,
                            @RequestParam(value = "sessionKey")String sessionKey){

        String checkStr = stringRedisTemplate.opsForValue().get(SESSION_KEY_PRE + ":" + sessionKey);

        if("login success".equals(checkStr)){

            Map<String,Object> resultData = new HashMap<>();

            // 1.取出商品的ids
            String[] ids = cartId.split(",");
            List<Integer> idsList = new ArrayList<>();
            for (String id: ids){
                idsList.add(Integer.parseInt(id));
            }

            // 2.从购物车里面查询出商品
            List<ShoppingCarViewData> products = shoppingCarService.productViewDataByIds(idsList);
            if(products != null){
                // 3.计算商品总价
                total = BigDecimal.ZERO;
                total = shoppingCarService.computeShoppingPrice(idsList);
                // 如果订单金额小于N元就加1元的运费
                Integer comp = total.compareTo(new BigDecimal(STARTING_PRICE));
                if(comp < 0){
                    total = total.add(new BigDecimal(1));
                }
                resultData.put("pro",products);
                resultData.put("price",total);
            }


            // 3.查询用户优惠券
            List<UserVoucher> userVoucherList = userVoucherService.selectByUid(uid);
            resultData.put("vou",userVoucherList);

            if(userVoucherList == null){
                resultData.put("vou","");
            }

            // 4.查询出用户默认地址
            Address address = addressService.selectDefaultByUid(uid);
            if(address != null){
                resultData.put("adds",address);
                resultData.put("addemt",0);
            }
            return JsonData.success(1,"查询成功",resultData);
        }
        throw new MallException(MallStatusEnum.CLIENT_CHECK_FAIL);
    }

}