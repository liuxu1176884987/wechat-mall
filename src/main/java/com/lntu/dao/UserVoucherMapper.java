package com.lntu.dao;

import com.lntu.entity.UserVoucher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserVoucherMapper {
    // 插入记录
    Integer insert(UserVoucher userVoucher);
    // 按uid查找记录
    List<UserVoucher> selectByUid(@Param(value = "uid")Integer uid);
    // 按照vid和uid来查找优惠券
    UserVoucher selectByVidUid(@Param(value = "vid")Integer vid,@Param(value = "uid")Integer uid);
    // 按vid和uid来更新优惠券状态
    Integer updateByVidUid(UserVoucher userVoucher);
}