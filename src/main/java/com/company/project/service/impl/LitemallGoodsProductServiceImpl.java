package com.company.project.service.impl;

import com.company.project.dao.LitemallGoodsProductMapper;
import com.company.project.model.LitemallGoodsProduct;
import com.company.project.service.LitemallGoodsProductService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGen.CodeGenerator on 2019/01/08.
 */
@Service
public class LitemallGoodsProductServiceImpl extends AbstractService<LitemallGoodsProduct> implements LitemallGoodsProductService {
    @Resource
    private LitemallGoodsProductMapper litemallGoodsProductMapper;

}
