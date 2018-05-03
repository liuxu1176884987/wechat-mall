package com.lntu.utils;

import com.lntu.enums.MallStatusEnum;
import com.lntu.exception.MallException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class LoginCheckUtil {

    // Redis服务
    @Autowired
    private  StringRedisTemplate stringRedisTemplate;

    // 获取前缀
    @Value("${LOGIN.SESSION_KEY_PRE}")
    private  String SESSION_KEY;

    public  Boolean check(String sessionKey){
        String checkStr = stringRedisTemplate.opsForValue().get(SESSION_KEY + ":" + sessionKey);
        if("login success".equals(checkStr)){
            return true;
        }else {
            throw new MallException(MallStatusEnum.CLIENT_CHECK_FAIL);
        }
    }

}
