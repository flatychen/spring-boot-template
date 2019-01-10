package com.conpany.project.service;

import com.company.project.core.join.ServiceJoinHelper;
import com.company.project.model.LitemallGoods;
import com.company.project.service.LitemallBrandService;
import com.company.project.service.LitemallCategoryService;
import com.company.project.service.LitemallGoodsProductService;
import com.company.project.service.LitemallGoodsService;
import com.conpany.project.Tester;
import com.google.common.base.Stopwatch;
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
        ServiceJoinHelper.join(LitemallGoods.class, goods, litemallCategoryService, litemallBrandService);
        System.out.println(goods);

    }

    @Test
    public void perTest() {
        List<LitemallGoods> goods = litemallGoodsService.findAll();
        for (int i = 0; i < 20; i++) {
            ServiceJoinHelper.join(LitemallGoods.class, goods, litemallCategoryService, litemallBrandService);
        }

    }

    @Test
    public void perTest2() {

        for (int i = 0; i < 20; i++) {
            Stopwatch stopwatch = Stopwatch.createStarted();
            List<LitemallGoods> goods = litemallGoodsService.findAll();
            stopwatch.stop();
            log.info("join use time:{}", stopwatch.toString());
        }

    }


}

