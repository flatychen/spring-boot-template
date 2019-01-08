package com.company.project.service.impl;

import com.company.project.dao.LitemallBrandMapper;
import com.company.project.model.LitemallBrand;
import com.company.project.service.LitemallBrandService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGen.CodeGenerator on 2019/01/08.
 */
@Service
public class LitemallBrandServiceImpl extends AbstractService<LitemallBrand> implements LitemallBrandService {
    @Resource
    private LitemallBrandMapper litemallBrandMapper;

}
