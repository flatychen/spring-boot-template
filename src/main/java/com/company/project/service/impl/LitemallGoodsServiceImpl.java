package com.company.project.service.impl;

import com.company.project.dao.LitemallGoodsMapper;
import com.company.project.model.LitemallGoods;
import com.company.project.service.LitemallGoodsService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGen.CodeGenerator on 2019/01/08.
 */
@Service
public class LitemallGoodsServiceImpl extends AbstractService<LitemallGoods> implements LitemallGoodsService {
    @Resource
    private LitemallGoodsMapper litemallGoodsMapper;

}
