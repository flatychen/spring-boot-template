package com.conpany.project.service;

import com.company.project.core.join.ServiceJoinHelper;
import com.company.project.model.LitemallGoods;
import com.company.project.service.LitemallBrandService;
import com.company.project.service.LitemallCategoryService;
import com.company.project.service.LitemallGoodsProductService;
import com.company.project.service.LitemallGoodsService;
import com.conpany.project.Tester;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class GoodsTest extends Tester {

    @Autowired
    LitemallCategoryService litemallCategoryService;


    @Autowired
    LitemallGoodsProductService litemallGoodsProductService;

    @Autowired
    LitemallGoodsService litemallGoodsService;


    @Autowired
    LitemallBrandService litemallBrandService;


    @Test
    public void test() {
        List<LitemallGoods> products = litemallGoodsService.findAll();
        ServiceJoinHelper.join(LitemallGoods.class, products, litemallGoodsService,litemallGoodsProductService);
        System.out.println(products);

    }



    @Test
    public void test2() {
        List<LitemallGoods> goods = litemallGoodsService.findAll();
        ServiceJoinHelper.join(LitemallGoods.class, goods, litemallCategoryService,litemallBrandService,litemallGoodsProductService);
        System.out.println(goods);

    }


}

