package com.lntu.dao;

import com.lntu.entity.AdminUser;

public interface AdminUserMapper {
    // 添加用户
    int insert(AdminUser adminUser);
    // 按照open_id查询用户
    AdminUser selectBuOpenid(String openId);
}