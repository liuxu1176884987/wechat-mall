package com.lntu.controller;

import com.lntu.entity.UserVoucher;
import com.lntu.enums.MallStatusEnum;
import com.lntu.exception.MallException;
import com.lntu.service.ProductService;
import com.lntu.service.ShoppingCarService;
import com.lntu.service.UserVoucherService;
import com.lntu.utils.WechatRequestCheck;
import com.lntu.view.ShoppingCarViewData;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
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

    // 商品总价格
    private BigDecimal total = BigDecimal.ZERO;

    @PostMapping(value = "buy_cart")
    public Map buyCart(@RequestParam(value = "cart_id")String cartId,
                       @RequestParam(value = "uid")String uid,
                       HttpServletRequest request){

        if(WechatRequestCheck.check(request,token)){
            // 1.按ids查询商品
            String[] ids = cartId.split(",");
            List<Integer> idsList = new ArrayList<>();
            for (String id: ids){
                idsList.add(Integer.parseInt(id));
            }
            // 返回参数
            Map<String,Object> resultData = new HashedMap();

            // 2.从购物车里面查询出商品
            List<ShoppingCarViewData> products = shoppingCarService.productViewDataByIds(idsList);

            if(products != null){
                // 3.计算商品总价
                total = BigDecimal.ZERO;
                products.forEach(product->{
                    total = total.add(product.getPrice().multiply(new BigDecimal(product.getNum())));
                });
                resultData.put("pro",products);
                resultData.put("price",total);
            }else {
                Map<String,Object> params = new HashedMap();
                params.put("cart_id",cartId);
                throw new MallException(MallStatusEnum.PRO_SELECT_FAIL,params);
            }


            // 3.查询用户优惠券
            List<UserVoucher> userVoucherList = userVoucherService.selectByUid(uid);
            resultData.put("vou",userVoucherList);
            if(userVoucherList == null){
                resultData.put("vou","");
            }

            resultData.put("addemt",null);

            return resultData;
        }
        Map<String,Object> params = new HashedMap();
        params.put("cartId",cartId);
        throw new MallException(MallStatusEnum.CLIENT_CHECK_FAIL,params);
    }

}
