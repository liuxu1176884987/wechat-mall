package com.lntu.dao;

import com.lntu.entity.ShoppingCar;
import com.lntu.view.ShoppingCarViewData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/4/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingCarMapperTest {

    @Autowired
    private ShoppingCarMapper shoppingCarMapper;

    @Test
    public void add() throws Exception {
        ShoppingCar shoppingCar = new ShoppingCar();
        shoppingCar.setPid(11111);
        shoppingCar.setPrice(new BigDecimal(123.45));
        shoppingCar.setNum(3333);
        shoppingCar.setBuff("属性");
        shoppingCar.setAddtime(new Date());
        shoppingCar.setUid(0);
        shoppingCar.setShopId(2);
        shoppingCar.setGid(3);
        shoppingCar.setType(new Byte("2"));
        shoppingCarMapper.add(shoppingCar);
        System.out.println(shoppingCar.getId());
    }

    @Test
    public void selectShoppingByUid(){
        List<ShoppingCar> shoppingList = shoppingCarMapper.selectShoppingByUid(0);
        System.out.println(shoppingList);
    }

    @Test
    public void selectAllByUid(){
        List<ShoppingCar> list = shoppingCarMapper.selectShoppingByUid(0);
        System.out.println(list);
    }

    @Test
    public void updateByUidId(){
        Integer result = shoppingCarMapper.updateByUidId(0, 10, 11);
        System.out.println(result);
    }

    @Test
    public void selectByUidPid(){
        ShoppingCar shoppingCar = shoppingCarMapper.selectByUidPid(0, 269);
        System.out.println(shoppingCar);
    }

    @Test
    public void updateByUidPid(){
        ShoppingCar shoppingCar = new ShoppingCar();
        shoppingCar.setPid(273);
        shoppingCar.setPrice(new BigDecimal(123.45));
        shoppingCar.setNum(3333);
        shoppingCar.setBuff("哈哈,测试成功了");
        shoppingCar.setAddtime(new Date());
        shoppingCar.setUid(0);
        shoppingCar.setShopId(2);
        shoppingCar.setGid(3);
        shoppingCar.setType(new Byte("2"));
        shoppingCarMapper.updateByUidPid(shoppingCar);
    }

    @Test
    public void deleteById(){
        Integer result = shoppingCarMapper.deleteByCartId(12);
        sop(result);
    }

    public void sop(Object obj){
        System.out.println(obj);
    }

    @Test
    public void selectByIds(){
        List<Integer> ids = new ArrayList<>();
        ids.add(264);
        List<ShoppingCarViewData> products = shoppingCarMapper.selectByIds(ids);
        sop(products);
    }


}