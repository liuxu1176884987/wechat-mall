package com.lntu.service.impl;


import com.lntu.dao.UserMapper;
import com.lntu.entity.User;
import com.lntu.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;

/**
 * 用户操作实现
 * Created by lx-pc on 2018/4/15.
 */
@Service
public class UserServiceImpl implements UserService {

    // 用户操作
    @Autowired
    private UserMapper userMapper;

    // 添加用户
    @Override
    @Transactional
    public Integer add(User user){
        // 1.添加用户返回用户的id
        if(user != null){
            User status = userMapper.selectByOpenid(user.getOpenid());
            if(status == null){
                // 如果用户不存在就增加用户
                userMapper.insert(user);
                return user.getId();
            }else {
                // 如果用户存在就更新用户信息
                try {
                    BeanUtils.copyProperties(user,status);
                }catch (Exception e){}
                userMapper.updateByOpenid(status);
                return status.getId();
            }
        }
        return null;
    }

}