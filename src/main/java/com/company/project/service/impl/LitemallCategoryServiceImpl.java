package com.company.project.service.impl;

import com.company.project.dao.LitemallCategoryMapper;
import com.company.project.model.LitemallCategory;
import com.company.project.service.LitemallCategoryService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGen.CodeGenerator on 2019/01/08.
 */
@Service
public class LitemallCategoryServiceImpl extends AbstractService<LitemallCategory> implements LitemallCategoryService {
    @Resource
    private LitemallCategoryMapper litemallCategoryMapper;

}
