package com.lntu.service.impl;

import com.github.pagehelper.PageHelper;
import com.lntu.dao.ProductMapper;
import com.lntu.dao.ProductScMapper;
import com.lntu.entity.Product;
import com.lntu.entity.ProductSc;
import com.lntu.entity.ProductWithBLOBs;
import com.lntu.enums.MallStatusEnum;
import com.lntu.exception.MallException;
import com.lntu.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品实现
 * Created by lx-pc on 2018/4/3.
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    //商品操作
    @Autowired
    private ProductMapper productMapper;

    //商品收藏
    @Autowired
    private ProductScMapper productScMapper;

    @Override
    public List<Product> selectAll(Integer pageNum,Integer pageSize) {
        //在查询之前加入如下语句
        PageHelper.startPage(pageNum,pageSize,false);
        //1.查询所有商品信息
        List<Product> products = productMapper.selectAll();
        //2.按照进行排序
        Collections.sort(products);
        return products;
    }

    @Override
    public ProductWithBLOBs selectById(Integer id) {
        ProductWithBLOBs product = productMapper.selectById(id);
        if(product == null){
            log.error("商品查询失败 pro_id={}",id);
            throw new MallException(MallStatusEnum.PRO_SELECT_FAIL);
        }
        return product;
    }

    @Override
    @Transactional
    public Integer productSc(Integer pid,Integer uid) {
        // 1.查询数据库,检验用户是否收藏过
        ProductSc checkProductSc = productScMapper.selectByUidPid(uid, pid);

        // 2.如果没有收藏过就插入数据库
        if(checkProductSc==null){
            ProductSc productSc = new ProductSc(pid,uid,new Byte("1"));
            Integer resultStatus = productScMapper.insert(productSc);
            if(resultStatus <=0 ){
                Map<String,Object> params = new HashMap<>();
                params.put("pid",pid);
                params.put("uid",uid);
                throw new MallException(MallStatusEnum.PRODUCT_SC_FAIL,params);
            }
            return 1;
        }else {
            // 3.如果有收藏过,执行取消收藏的操作
            if(checkProductSc.getStatus() == 0){
                productScMapper.updateByUidPid(uid, pid, 1);
                return 1;
            }
            if(checkProductSc.getStatus() == 1){
                productScMapper.updateByUidPid(uid, pid, 0);
                return 0;
            }
        }
        return null;
        // throw new RuntimeException();
    }

    // 详情页初始化商品收藏
    @Override
    public Integer initSc(Integer pid, Integer uid) {
        ProductSc productSc = productScMapper.selectByUidPid(uid, pid);
        if(productSc != null && productSc.getStatus() == 1){
            return 1;
        }
        return 0;
    }

    @Override
    public List<Product> selectByIds(List ids) {
        List<Product> products = productMapper.selectByIds(ids);
        if(products!=null){
            return products;
        }
        Map<String,Object> params = new HashedMap();
        params.put("ids",ids);
        throw new MallException(MallStatusEnum.PRO_SELECT_FAIL,params);
    }

    @Override
    public List<ProductSc> selectByUid(Integer uid) {
        return productScMapper.selectByUid(uid);
    }


}