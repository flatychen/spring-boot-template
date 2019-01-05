package com.conpany.project.service;

import com.company.project.core.join.ServiceJoinHelper;
import com.company.project.model.LuckyBag;
import com.company.project.service.LuckyBagService;
import com.conpany.project.Tester;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LuckyBagServiceTest extends Tester {

    @Autowired
    LuckyBagService luckyBagService;

    @Test
    public void test() {
        List<LuckyBag> luckyBags =  luckyBagService.findAll();
        try {
            ServiceJoinHelper.join(LuckyBag.class,luckyBags,luckyBagService);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }


}

