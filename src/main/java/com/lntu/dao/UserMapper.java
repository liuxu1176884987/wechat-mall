package com.lntu.dao;

import com.lntu.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    // 添加用户
    Integer insert(User user);
    // 按照openid查询用户
    User selectByOpenid(@Param(value = "openid") String openid);
    // 按照openid修改用户
    Integer updateByOpenid(User user);
}