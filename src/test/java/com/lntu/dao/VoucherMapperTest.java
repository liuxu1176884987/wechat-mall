package com.lntu.dao;

import com.lntu.entity.Voucher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by lx-pc on 2018/4/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VoucherMapperTest {

    @Autowired
    private VoucherMapper voucherMapper;

    @Test
    public void insert() throws Exception {
        Voucher voucher = new Voucher();
        voucher.setShopId(555);
        voucher.setTitle("端午节活动");
        voucher.setFullMoney(new BigDecimal(500));
        voucher.setAmount(new BigDecimal(100));
        voucher.setStartTime(new Date());
        voucher.setEndTime(new Date());
        voucher.setPoint(0);
        voucher.setCount(30);
        voucher.setReceiveNum(1);
        voucher.setAddtime(new Date());
        voucher.setType(true);
        voucher.setDel(true);
        voucher.setProid("sdfasdfasdfsdf");
        voucherMapper.insert(voucher);
    }

    @Test
    public void selectAll(){
        List<Voucher> vouchers = voucherMapper.selectAll();
        System.out.println(vouchers);
    }

}