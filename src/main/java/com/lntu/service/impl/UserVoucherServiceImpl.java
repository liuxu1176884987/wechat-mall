package com.lntu.service.impl;

import com.lntu.dao.UserVoucherMapper;
import com.lntu.entity.UserVoucher;
import com.lntu.service.UserVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户优惠券服务
 * Created by lx-pc on 2018/4/17.
 */
@Service
public class UserVoucherServiceImpl implements UserVoucherService {

    // 优惠券服务
    @Autowired
    private UserVoucherMapper userVoucherMapper;

    @Override
    public List<UserVoucher> selectByUid(String uid) {
        if(!"".equals(uid)){
            List<UserVoucher> voucherList = userVoucherMapper.selectByUid(uid);
            return voucherList;
        }
        return null;
    }
}
