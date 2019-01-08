package com.conpany.project.mapper;

import com.company.project.dao.LitemallGoodsMapper;
import com.company.project.model.LitemallGoods;
import com.conpany.project.Tester;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

public class GoodsMapperTest extends Tester {

    @Autowired
    LitemallGoodsMapper litemallGoodsMapper;


    public void test() {
        Condition condition = new Condition(LitemallGoods.class);
//        condition.createCriteria().and
        condition.and();
       List<LitemallGoods> litemallGoods =  litemallGoodsMapper.selectByCondition(condition);

    }

}

