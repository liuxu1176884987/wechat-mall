package com.lntu.controller;

import com.lntu.entity.User;
import com.lntu.enums.MallStatusEnum;
import com.lntu.exception.MallException;
import com.lntu.service.UserService;
import com.lntu.utils.WechatRequestCheck;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * 登陆接口
 * Created by lx-pc on 2018/4/15.
 */
@RestController
@RequestMapping(value = "/Api/Login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Value("${wechat-request-token}")
    private String token;

    // 登陆
    @PostMapping(value = "authlogin")
    public Map authLogin(@RequestParam(value = "gender")Byte gender,
                         @RequestParam(value = "NickName")String nickName,
                         @RequestParam(value = "HeadUrl")String headUrl,
                         @RequestParam(value = "openid")String openid,
                         HttpServletRequest request){

        if(WechatRequestCheck.check(request,token)){
            User user = new User();
            user.setOpenid(openid);
            user.setNickName(nickName);
            user.setPhoto(headUrl);
            user.setSex(gender);
            user.setName("");
            user.setPwd("");
            user.setJifen((float) 0);
            user.setTel("");
            user.setQqId("");
            user.setEmail("");
            user.setSource("wechat");
            user.setAddtime(new Date());
            try {
                String sessionKey = userService.add(user);
                Map<String,Object> resultData = new HashedMap();
                if(sessionKey != null){
                    resultData.put("status",1);
                    resultData.put("sessionKey",sessionKey);
                }else {
                    resultData.put("status",500);
                    resultData.put("err","登陆失败!");
                }
                return resultData;
            }catch (Exception e){}
        }
        Map<String,Object> params = new HashedMap();
        params.put("openid",openid);
        params.put("NickName",nickName);
        params.put("headUrl",headUrl);
        params.put("gender",gender);
        throw new MallException(MallStatusEnum.CLIENT_CHECK_FAIL,params);
    }

}
