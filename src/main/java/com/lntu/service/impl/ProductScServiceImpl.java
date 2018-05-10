package com.lntu.service.impl;

import com.github.pagehelper.PageHelper;
import com.lntu.dao.ProductScMapper;
import com.lntu.service.ProductScService;
import com.lntu.view.ProductViewData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductScServiceImpl implements ProductScService {

    // 商品收藏服务
    @Autowired
    private ProductScMapper productScMapper;

    /**
     * <pre>
     *     查询用户收藏的商品列表
     * </pre>
     * @param uid 用户id
     * */
    @Override
    public List<ProductViewData> selectProductScByUid(Integer pageindex,Integer pagesize,Integer uid) {
        PageHelper.startPage(pageindex,pagesize,false);
        return productScMapper.selectProductScByUid(uid);
    }

    /**
     * @param scId 收藏id
     * @param status 收藏状态
     * */
    @Override
    public Integer updateProductScStatus(Integer uid,Integer scId, Integer status) {
        return productScMapper.updateByUidPid(uid,scId,status);
    }

}