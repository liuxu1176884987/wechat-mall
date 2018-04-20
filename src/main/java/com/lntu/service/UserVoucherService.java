package com.lntu.service;

import com.lntu.entity.UserVoucher;

import java.util.List;

/**
 * 用户优惠券服务
 * Created by lx-pc on 2018/4/17.
 */
public interface UserVoucherService {
    // 按uid查找优惠券
    List<UserVoucher> selectByUid(String uid);
}
