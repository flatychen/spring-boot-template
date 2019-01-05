package com.company.project.service.impl;

import com.company.project.dao.LuckyBagMapper;
import com.company.project.model.LuckyBag;
import com.company.project.service.LuckyBagService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGen.CodeGenerator on 2019/01/05.
 */
@Service
public class LuckyBagServiceImpl extends AbstractService<LuckyBag> implements LuckyBagService {
    @Resource
    private LuckyBagMapper luckyBagMapper;

}
