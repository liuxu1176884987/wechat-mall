package com.lntu.service.impl;

import com.lntu.dao.UserVoucherMapper;
import com.lntu.entity.UserVoucher;
import com.lntu.service.UserVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    public List<UserVoucher> selectByUid(Integer uid) {
        if(!"".equals(uid)){
            List<UserVoucher> voucherList = userVoucherMapper.selectByUid(uid);
            return voucherList;
        }
        return null;
    }

    /**
     * @param vid 优惠券id
     * @param uid 用户id
     * @return result 减了多少钱
     * */
    @Override
    @Transactional
    public BigDecimal useVoucher(Integer vid, Integer uid,BigDecimal total) {
        UserVoucher userVoucher = userVoucherMapper.selectByVidUid(vid, uid);
        if(userVoucher != null){
            // 判断当前消费是否满足优惠券的满N元
            Integer result = userVoucher.getFullMoney().compareTo(total);
            if(result <= 0){
                //可以使用优惠券
                userVoucher.setStatus(new Byte("2"));
                Integer resultStatus = userVoucherMapper.updateByVidUid(userVoucher);
                if(resultStatus > 0){
                    // 修改优惠券状态成功,返回可优惠的价格
                    return userVoucher.getAmount();
                }
            }
        }
        return new BigDecimal(0);
    }

}