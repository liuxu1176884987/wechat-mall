package com.lntu.service;

import com.lntu.entity.UserVoucher;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 用户优惠券服务
 * Created by lx-pc on 2018/4/17.
 */
public interface UserVoucherService {
    // 按uid查找优惠券
    List<UserVoucher> selectByUid(Integer uid);
    // 按vid和uid来查询优惠券
    BigDecimal useVoucher(Integer vid, Integer uid,BigDecimal total);
}