package com.lntu.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lntu.enums.MallStatusEnum;
import com.lntu.exception.MallException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * controller拦截
 * Created by lx-pc on 2018/4/19.
 */
@Aspect
@Component
public class ControllerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${REDIS_KEY.LOGIN}")
    private String LOGIN_KEY;

    @Around(value = "execution(public * com.lntu.controller.AddressController.delAdds(..))")
    public Map login(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        String sessionKey = (String)args[1];
        String result = stringRedisTemplate.opsForValue().get(LOGIN_KEY + ":" + sessionKey);
        Map<String,Object> resultData = null;
        if(result != null){
            resultData = (Map<String, Object>) pjp.proceed();
        }else {
            throw new MallException(MallStatusEnum.CLIENT_CHECK_FAIL);
        }
        return resultData;
    }

}