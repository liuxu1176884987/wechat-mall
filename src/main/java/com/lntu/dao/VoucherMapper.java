package com.lntu.dao;

import com.lntu.entity.Voucher;

import java.util.List;

public interface VoucherMapper {
    // 插入优惠券记录
    Integer insert(Voucher voucher);
    // 查询所有优惠券
    List<Voucher> selectAll();
}