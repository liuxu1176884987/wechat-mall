package com.lntu.dao;

import com.lntu.entity.UserVoucher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserVoucherMapper {
    // 插入记录
    Integer insert(UserVoucher userVoucher);
    // 按uid查找记录
    List<UserVoucher> selectByUid(@Param(value = "uid")String uid);
}