package com.lntu.controller;

import com.lntu.common.JsonData;
import com.lntu.enums.MallStatusEnum;
import com.lntu.enums.OrderStatusEnum;
import com.lntu.exception.MallException;
import com.lntu.service.OrderService;
import com.lntu.utils.LoginCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户接口
 * */
@RestController
@RequestMapping(value = "/Api/User")
public class UserController {

    // 校验客户端
    @Autowired
    private LoginCheckUtil loginCheckUtil;

    // 订单服务
    @Autowired
    private OrderService orderService;


    @PostMapping(value = "getorder")
    @ResponseStatus(value = HttpStatus.OK)
    public JsonData getOrder(@RequestParam(value = "userId")Integer userId,
                             @RequestParam(value = "sessionKey")String sessionKey){
        // 校验逻辑
        Boolean check = loginCheckUtil.check(sessionKey);
        if(check){

            // 1.查询待付款订单个数
            Integer waitPay = orderService.countOrderStatus(userId,OrderStatusEnum.ORDER_NOT_PAY.getStatus());

            // 2.查询待收货订单个数
            Integer waitReceipt = orderService.countOrderStatus(userId,OrderStatusEnum.WAIT_RECEIPT.getStatus());

            // 3.查询已完成订单个数
            Integer orderFinish = orderService.countOrderStatus(userId,OrderStatusEnum.ORDER_FINISH.getStatus());

            Map<String,Object> resultData = new HashMap<>();
            resultData.put("pay_num",waitPay);
            resultData.put("rec_num",waitReceipt);
            resultData.put("finish_num",orderFinish);
            resultData.put("refund_num",0);

            return JsonData.success(1,"订单查询成功",resultData);
        }
        throw new MallException(MallStatusEnum.CLIENT_CHECK_FAIL);
    }
}
