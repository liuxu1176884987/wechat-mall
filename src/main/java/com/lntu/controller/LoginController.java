package com.lntu.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.lntu.common.JsonData;
import com.lntu.entity.User;
import com.lntu.enums.MallStatusEnum;
import com.lntu.exception.MallException;
import com.lntu.service.UserService;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 登陆接口
 * Created by lx-pc on 2018/4/15.
 */
@RestController
@RequestMapping(value = "/Api/Login")
public class LoginController {

    @Autowired
    private WxMaService wxMaService;

    @Autowired
    private UserService userService;

    // redis服务
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${LOGIN.SESSION_KEY_PRE}")
    private String SESSION_KEY_PRE;

    /**
     * @param  code 从前端传来的code来换取openid和sessionId
     * */
    @PostMapping(value = "getsessionkey")
    @ResponseStatus(value = HttpStatus.OK)
    public JsonData getsessionkey(@RequestParam(value = "code")String code,
                                  HttpSession session){

        try {
            // 1.获取用户的sessionId 和 openid
            WxMaJscode2SessionResult sessionInfo = wxMaService.getUserService().getSessionInfo(code);
            Map<String,Object> resultData = new HashedMap();
            if(sessionInfo != null){
                resultData.put("session_key",sessionInfo.getSessionKey());
                resultData.put("openid",sessionInfo.getOpenid());
                // 设置session有效时间为30分钟
                stringRedisTemplate.opsForValue().set(SESSION_KEY_PRE+":"+sessionInfo.getSessionKey(),"login success",30,TimeUnit.MINUTES);
                return JsonData.success(1,"登录成功",resultData);
            }else {
                return JsonData.fail(0,"登录失败");
            }
        } catch (WxErrorException e) {
            // 获取用户信息失败
            Map<String,Object> errParamInfo = new HashedMap();
            errParamInfo.put("code",code);
            throw new MallException(MallStatusEnum.GET_USER_INFO_FAIL,errParamInfo);
        }
    }

    /**
     * @param gender 性别
     * @param nickName 昵称
     * @param headUrl 头像地址
     * @param openid 微信用户唯一标识
     * */
    @PostMapping(value = "authlogin")
    @ResponseStatus(value = HttpStatus.OK)
    public JsonData authlogin(@RequestParam(value = "gender")Byte gender,
                              @RequestParam(value = "NickName")String nickName,
                              @RequestParam(value = "HeadUrl")String headUrl,
                              @RequestParam(value = "openid")String openid,
                              @RequestParam(value = "SessionId")String sessionId,
                              HttpSession session){

        String result = stringRedisTemplate.opsForValue().get(SESSION_KEY_PRE+":" + sessionId);
        if("login success".equals(result)){
            // 1.新建用户对象
            User user = new User();
            user.setName("");
            user.setPwd("");
            user.setNickName(nickName);
            user.setPhoto(headUrl);
            user.setSex(gender);
            user.setOpenid(openid);
            user.setAddtime(new Date());
            user.setSource("wechat");
            // 2.添加用户信息
            Integer userId = userService.add(user);

            // 3.返回前端数据
            Map<String,Object> resultData = new HashMap<>();
            resultData.put("id",userId);
            resultData.put("NickName",nickName);
            resultData.put("HeadUrl",headUrl);
            return JsonData.success(1,"登陆成功",resultData);
        }else {
            return JsonData.fail(402,"登录失败");
        }
    }

}
