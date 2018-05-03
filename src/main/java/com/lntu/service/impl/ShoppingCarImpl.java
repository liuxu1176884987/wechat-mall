package com.lntu.service.impl;

import com.lntu.dao.ProductMapper;
import com.lntu.dao.ShoppingCarMapper;
import com.lntu.entity.ShoppingCar;
import com.lntu.service.ProductService;
import com.lntu.service.ShoppingCarService;
import com.lntu.view.ShoppingCarViewData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车服务
 * Created by lx-pc on 2018/4/10.
 */
@Service
public class ShoppingCarImpl implements ShoppingCarService {

    // 购物车服务
    @Autowired
    private ShoppingCarMapper shoppingCarMapper;

    // 商品服务
    @Autowired
    private ProductMapper productMapper;

    // 购物车商品总价格
    private BigDecimal totalPrice = BigDecimal.ZERO;

    @Override
    @Transactional
    public Integer add(ShoppingCar shoppingCar) {
        if(shoppingCar != null){
            ShoppingCar result = shoppingCarMapper.selectByUidPid(shoppingCar.getUid(), shoppingCar.getPid());
            if(result != null){
                // 如果查询到记录,证明曾经收藏过,如果收藏过就把商品的数量+1
                result.setNum(result.getNum()+shoppingCar.getNum());
                shoppingCarMapper.updateByUidPid(result);
                return result.getId();
            }
            shoppingCarMapper.add(shoppingCar);
            return shoppingCar.getId();
        }
        return null;
    }

    @Override
    public List<ShoppingCar> shoppingCarList(Integer uid) {
        List<ShoppingCar> shoppingCars = shoppingCarMapper.selectShoppingByUid(uid);
        if(shoppingCars!=null && shoppingCars.size()>0){
            return shoppingCars;
        }
        return null;
    }

    @Override
    public Integer updateByUidId(Integer uid, Integer id, Integer num) {
        return shoppingCarMapper.updateByUidId(uid,id,num);
    }

    @Override
    @Transactional
    public Integer deleteByCartId(Integer cid) {
        Integer result = shoppingCarMapper.deleteByCartId(cid);
        return result;
    }

    @Override
    public List<ShoppingCarViewData> productViewDataByIds(List ids) {
        return shoppingCarMapper.selectByIds(ids);
    }

    /**
     * @param ids 购物车id
     * @return null 计算出的购物车商品总价格
     * */
    @Override
    public BigDecimal computeShoppingPrice(List<Integer> ids) {
        List<ShoppingCarViewData> shoppingCarViewData = shoppingCarMapper.selectByIds(ids);
        totalPrice = BigDecimal.ZERO;
        shoppingCarViewData.forEach(shoppingCar->{
            totalPrice = totalPrice.add(shoppingCar.getPriceYh().multiply(new BigDecimal(shoppingCar.getNum())));
        });
        return totalPrice;
    }

}