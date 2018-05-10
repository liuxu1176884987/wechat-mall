package com.lntu.controller;

import com.lntu.common.JsonData;
import com.lntu.entity.Address;
import com.lntu.entity.Order;
import com.lntu.entity.OrderProduct;
import com.lntu.enums.MallStatusEnum;
import com.lntu.enums.OrderStatusEnum;
import com.lntu.exception.MallException;
import com.lntu.service.*;
import com.lntu.utils.LoginCheckUtil;
import com.lntu.view.OrderDetailViewData;
import com.lntu.view.ShoppingCarViewData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping(value = "/Api/Order")
public class OrderController {

    // 校验session服务
    @Autowired
    private LoginCheckUtil loginCheckUtil;

    // 订单服务
    @Autowired
    private OrderService orderService;

    // 订单商品服务
    @Autowired
    private OrderProductService orderProductService;

    // 购物车服务
    @Autowired
    private ShoppingCarService shoppingCarService;

    // 订单起送价格
    @Value("${starting_price}")
    private BigDecimal startingPrice;

    // 优惠券服务
    @Autowired
    private UserVoucherService userVoucherService;

    // 地址服务
    @Autowired
    private AddressService addressService;


    /**
     * @param uid 用户id
     * @param cartId 购物车id
     * @param type 支付类型
     * @param addressId 地址id
     * @param remark 用户备注
     * @param vid 优惠卷id
     * @return null
     * */
    @PostMapping(value = "createOrder")
    public JsonData createOrder(@RequestParam(value = "uid")Integer uid,
                                @RequestParam(value = "cart_id")String cartId,
                                @RequestParam(value = "type")String type,
                                @RequestParam(value = "aid")Integer addressId,
                                @RequestParam(value = "remark")String remark,
                                @RequestParam(value = "vid")Integer vid,
                                @RequestParam(value = "sessionKey")String sessionKey){

        Boolean check = loginCheckUtil.check(sessionKey);
        if(check){
            // 1.创建一个订单类
            Order order = new Order();
            // 用uuid作为订单编号
            String orderSn = UUID.randomUUID().toString().replace("-", "");
            order.setShopId(0);
            order.setOrderSn(orderSn);
            order.setUid(uid);

            // 解析出购买商品的id
            String[] ids = cartId.split(",");
            List idList = new ArrayList();
            for(Integer i=0;i<ids.length;i++){
                Integer id = Integer.parseInt(ids[i]);
                idList.add(id);
            }

            // 计算出商品总价格
            BigDecimal totalPrice = shoppingCarService.computeShoppingPrice(idList);
            order.setPrice(totalPrice);

            int bool = totalPrice.compareTo(startingPrice);
            if(bool < 0){
                totalPrice = totalPrice.add(new BigDecimal(1));
            }

            // 计算优惠券价格
            if(vid != null){
                BigDecimal voucherPrice = userVoucherService.useVoucher(vid, uid,totalPrice);
                totalPrice = totalPrice.subtract(voucherPrice);
                // 当使用优惠券减出负价格要把价格强制为0
                int comp = totalPrice.compareTo(new BigDecimal(0));
                if(comp <= 0){
                    totalPrice = new BigDecimal(0);
                }
                order.setVid(vid);
            }
            order.setAmount(totalPrice);
            order.setAddtime(new Date());
            order.setDel(new Byte("0"));
            order.setType("weixin");

            // TODO 真实支付金额未设置
            order.setPriceH(new BigDecimal(0));

            // TODO 微信交易单号
            order.setTradeNo("");

            order.setStatus(new Byte("10"));
            Address address = addressService.selectById(addressId);
            if(address != null){
                order.setReceiver(address.getName());
                order.setTel(address.getTel());
                order.setAddressXq(address.getAddressXq());
                order.setCode(address.getCode());
            }
            order.setPost(0);
            order.setRemark(remark);
            order.setPostRemark("");
            order.setProductNum(ids.length);
            order.setOrderType(new Byte("1"));
            orderService.insertOrder(order);

            // 插入订单商品数据
            List<OrderProduct> orderProductList = new ArrayList<>();
            List<ShoppingCarViewData> productList = shoppingCarService.productViewDataByIds(idList);

            // TODO 支付单号未填写  规格未填写
            productList.forEach(product->{
                orderProductList.add(new OrderProduct(product.getProId(),"",order.getId(),product.getProName(),
                        product.getPriceYh(),product.getPhotoX(),"",new Date(),product.getNum(),""));
            });
            orderProductService.insertOrderProduct(orderProductList);

            Map<String,Object> resultData = new HashMap<>();
            resultData.put("pay_type","weixin");
            return JsonData.success(1,"创建订单成功",resultData);

        }
        return null;
    }

    /**
     * 查看订单
     * */
    @PostMapping(value = "index")
    public JsonData index(@RequestParam(value = "uid")Integer uid,
                          @RequestParam(value = "order_type")String orderType,
                          @RequestParam(value = "page")Integer page,
                          @RequestParam(value = "sessionKey")String sessionKey){
        Boolean check = loginCheckUtil.check(sessionKey);
        if(check){
            // 1.订单状态
            Integer orderStatus = null;
            if("pay".equals(orderType) || "undefined".equals(orderType)){
                orderStatus = OrderStatusEnum.ORDER_NOT_PAY.getStatus();
            }else if("receive".equals(orderType)){
                orderStatus = OrderStatusEnum.WAIT_RECEIPT.getStatus();
            }else if("finish".equals(orderType)){
                orderStatus = OrderStatusEnum.ORDER_FINISH.getStatus();
            }
            // 2.按uid和status来查询订单信息
            List<OrderDetailViewData> orderDetailViewData = orderProductService.selectOrderProductByUidStatus(uid, orderStatus);
            return JsonData.success(1,"订单商品查询成功",orderDetailViewData);
        }
        throw new MallException(MallStatusEnum.CLIENT_CHECK_FAIL);
    }

    /**
     * 取消订单
     * */
    @PostMapping(value = "orders_edit")
    public JsonData ordersEdit(@RequestParam(value = "id")Integer id,
                               @RequestParam(value = "sessionKey")String sessionKey){

        Boolean check = loginCheckUtil.check(sessionKey);
        if (check){
            Integer status = orderService.cancelOrder(id);
            if(status > 0){
                return JsonData.success(1,"取消订单成功",null);
            }
            // 取消订单
            throw new MallException(MallStatusEnum.CANCEL_ORDER_FAIL);
        }
        // 取消订单
        throw new MallException(MallStatusEnum.CLIENT_CHECK_FAIL);
    }

    @PostMapping(value = "order_details")
    public JsonData orderDetails(@RequestParam(value = "order_id")Integer orderId,
                                 @RequestParam(value = "sessionKey")String sessionKey){

        Boolean check = loginCheckUtil.check(sessionKey);
        if(check){
            // 1.查询订单
            Order order = orderService.selectOrderById(orderId);
            // 2.查询订单中的商品
            List<OrderDetailViewData> orderDetailViewData = orderProductService.selectOrderProductById(orderId);

            Map<String,Object> resultData = new HashMap<>();
            resultData.put("pro",orderDetailViewData);
            resultData.put("ord",order);

            return JsonData.success(1,"查询订单成功",resultData);
        }
        throw new MallException(MallStatusEnum.CLIENT_CHECK_FAIL);
    }
}