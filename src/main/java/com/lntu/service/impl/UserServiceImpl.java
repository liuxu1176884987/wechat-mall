package com.lntu.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lntu.dao.UserMapper;
import com.lntu.entity.User;
import com.lntu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 用户操作实现
 * Created by lx-pc on 2018/4/15.
 */
@Service
public class UserServiceImpl implements UserService {

    // 用户操作
    @Autowired
    private UserMapper userMapper;

    // redis string
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // json转换
    @Autowired
    private ObjectMapper objectMapper;

    // 注入登陆在redis中产生的key
    @Value("${REDIS_KEY.LOGIN}")
    private String LOGIN_KEY;

    @Value("${REDIS_LOGIN_FAILURE_TIME}")
    private Integer REDIS_LOGIN_FAILURE_TIME;

    @Override
    @Transactional
    public String add(User user) throws JsonProcessingException {
        if(user != null){
            User result = userMapper.selectByOpenid(user.getOpenid());

            String sessionKey = UUID.randomUUID().toString().replace("-","");
            // 把用户信息转换成json格式
            String userJson = objectMapper.writeValueAsString(result);

            if(result == null){
                Integer insertResult = userMapper.insert(user);
                if(insertResult > 0){
                    // 把sessionKey写入到redis中
                    stringRedisTemplate.opsForValue().set(LOGIN_KEY+":"+sessionKey,userJson,REDIS_LOGIN_FAILURE_TIME, TimeUnit.MINUTES);
                }
            }else {
                stringRedisTemplate.opsForValue().set(LOGIN_KEY+":"+sessionKey,userJson,REDIS_LOGIN_FAILURE_TIME, TimeUnit.MINUTES);
            }
            return sessionKey;
        }
        return null;
    }

}