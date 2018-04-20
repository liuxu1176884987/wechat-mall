package com.lntu.service.impl;

import com.lntu.dao.AdvMapper;
import com.lntu.entity.Adv;
import com.lntu.service.AdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 广告服务
 * Created by Administrator on 2018/4/4.
 */
@Service
public class AdvServiceImpl implements AdvService {

    @Autowired
    private AdvMapper advMapper;

    @Override
    public List<Adv> selectIndexAdv() {
        //1.查询出所有广告
        List<Adv> advList = advMapper.selectIndexAdv();
        //2.广告排序
        Collections.sort(advList);
        return advList;
    }

}
