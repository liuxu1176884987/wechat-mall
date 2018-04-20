package com.lntu.dao;

import com.lntu.entity.AdminUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by lx-pc on 2018/4/15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminUserMapperTest {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Test
    public void insert() throws Exception {
        AdminUser adminUser = new AdminUser();
        adminUser.setName("柳絮");
        adminUser.setNickName("哈哈123456798");
        adminUser.setPwd("xxxxxxxxxxx");
        adminUser.setQx(new Byte("1"));
        adminUser.setAddtime(new Date());
        adminUser.setDel(new Byte("0"));
        adminUserMapper.insert(adminUser);
    }

}