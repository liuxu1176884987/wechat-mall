package com.lntu.dao;

import com.lntu.entity.UserVoucher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by lx-pc on 2018/4/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserVoucherMapperTest {

    @Autowired
    private UserVoucherMapper userVoucherMapper;

    @Test
    public void insert() throws Exception {
        UserVoucher userVoucher = new UserVoucher();
        userVoucher.setUid(1);
        userVoucher.setVid(3333);
        userVoucher.setShopId(333);
        userVoucher.setFullMoney(new BigDecimal(200));
        userVoucher.setAmount(new BigDecimal(100));
        userVoucher.setStartTime(new Date());
        userVoucher.setEndTime(new Date());
        userVoucher.setAddtime(new Date());
        userVoucher.setStatus(new Byte("1"));
        userVoucherMapper.insert(userVoucher);
    }

    @Test
    public void selectByUid(){
        List<UserVoucher> list = userVoucherMapper.selectByUid(1);
        System.out.println(list);
    }

    @Test
    public void selectByVidUid(){
        UserVoucher userVoucher = userVoucherMapper.selectByVidUid(8, 10);
        System.out.println(userVoucher);
    }

    @Test
    public void updateByVidUid(){
        UserVoucher userVoucher = userVoucherMapper.selectByVidUid(8, 10);
        userVoucher.setStatus(new Byte("0"));
        userVoucher.setShopId(0);
        userVoucherMapper.updateByVidUid(userVoucher);
    }

}