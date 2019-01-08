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
public class GoodProductTest extends Tester {


    @Autowired
    LitemallBrandService litemallBrandService;


    @Autowired
    LitemallCategoryService litemallCategoryService;

    @Autowired
    LitemallGoodsService litemallGoodsService;

    @Autowired
    LitemallGoodsProductService litemallGoodsProductService;


    @Test
    public void test() {
        List<LitemallGoods> goods = litemallGoodsService.findAll();
        ServiceJoinHelper.join(LitemallGoods.class, goods, litemallCategoryService,litemallBrandService);
        System.out.println(goods);

    }



}

