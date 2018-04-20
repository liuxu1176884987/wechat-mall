package com.lntu.controller;

import com.lntu.entity.Adv;
import com.lntu.entity.Product;
import com.lntu.service.AdvService;
import com.lntu.service.ProductService;
import com.lntu.view.IndexViewData;
import com.lntu.view.ProductViewData;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 返回主页数据
 * Created by lx-pc on 2018/4/3.
 */
@RestController
@RequestMapping(value = "/Api/Index")
public class IndexController {

    //商品服务
    @Autowired
    private ProductService productService;

    //广告服务
    @Autowired
    private AdvService advService;

    @PostMapping(value = "index")
    public IndexViewData indexData(){
        //1.建立主页视图返回对象
        IndexViewData indexViewData = new IndexViewData();
        //2.查询所有商品信息并添加
        // 此处的30写死了,表示进入主页显示出30条数据
        List<Product> products = productService.selectAll(1,30);
        List<ProductViewData> productViewDataList = new ArrayList<>();

        products.forEach((product)->{
            ProductViewData productViewData = new ProductViewData();
            try {
                BeanUtils.copyProperties(productViewData,product);
            } catch (Exception e) {
                e.printStackTrace();
            }
            productViewDataList.add(productViewData);
        });
        indexViewData.setProlist(productViewDataList);

        //3.查询主页大广告位
        List<Adv> advs = advService.selectIndexAdv();
        List<String> advViewData = new ArrayList<>();
        advs.forEach((adv)->{
            advViewData.add(adv.getAction());
        });
        indexViewData.setGgtop(advViewData);

        //4.设置主页错误提示
        indexViewData.setErr("服务器打盹了,再试一次叫醒它");

        return indexViewData;
    }

    @PostMapping(value = "getlist")
    public IndexViewData getProductList(@RequestParam(value = "page")Integer page){
        // 此处的20写死了,是指没点击一次加载更新20条数据
        List<Product> products = productService.selectAll(page, 20);
        List<ProductViewData> moreListData = new ArrayList<>();
        products.forEach((product)->{
            ProductViewData productViewData = new ProductViewData();
            try {
                BeanUtils.copyProperties(productViewData,product);
                moreListData.add(productViewData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        IndexViewData indexViewData = new IndexViewData();
        indexViewData.setProlist(moreListData);
        if(indexViewData.getProlist() != null && indexViewData.getProlist().size()>0){
            return indexViewData;
        }
        return null;
    }

}