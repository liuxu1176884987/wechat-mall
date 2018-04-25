package com.lntu.dao;

import com.lntu.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by lx-pc on 2018/4/15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert(){
        User user = new User();
        user.setName("柳絮");
        user.setNickName("哈哈哈哈哈哈哈哈哈哈哈");
        user.setPwd("132465798");
        user.setJifen((float) 2.3);
        user.setPhoto("c://www.");
        user.setTel("18941809532");
        user.setEmail("wwwwwww");
        user.setOpenid("aaaaaaaaaaaaaaaaabbaa");
        user.setSource("wechat");
        userMapper.insert(user);
    }

    @Test
    public void selectByOpenid(){
        User user = userMapper.selectByOpenid("aaaaaaaaaaaaaaaaaaaaaa");
        System.out.println(user);
    }

    @Test
    public void updateByOpenid(){
        User user = new User();
        user.setName("柳絮22222222222222222");
        user.setNickName("哈哈哈哈哈哈哈哈哈哈哈");
        user.setPwd("132465798");
        user.setJifen((float) 2.3);
        user.setPhoto("c://www.");
        user.setTel("18941809532");
        user.setEmail("wwwwwww");
        user.setOpenid("aaaaaaaaaaaaaaaaabbaa");
        user.setSource("wechat");
        userMapper.updateByOpenid(user);
    }

}