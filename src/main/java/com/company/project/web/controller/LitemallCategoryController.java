package com.company.project.web.controller;
import com.company.project.base.Result;
import com.company.project.base.ResultCodeEnum;
import com.company.project.model.LitemallCategory;
import com.company.project.service.LitemallCategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGen.CodeGenerator on 2019/01/08.
*/
@RestController
@RequestMapping("/litemall/category")
public class LitemallCategoryController {
    @Resource
    private LitemallCategoryService litemallCategoryService;

    @RequestMapping("/add")
    public Object add(LitemallCategory litemallCategory) {
        litemallCategoryService.save(litemallCategory);
        return Result.success();
    }

    @RequestMapping("/delete")
    public Object delete(@RequestParam Integer id) {
        litemallCategoryService.deleteById(id);
        return Result.success();
    }

    @RequestMapping("/update")
    public Object update(LitemallCategory litemallCategory) {
        litemallCategoryService.update(litemallCategory);
        return Result.success();
    }

    @RequestMapping("/detail")
    public Object detail(@RequestParam Integer id) {
        LitemallCategory litemallCategory = litemallCategoryService.getById(id);
        return Result.success(litemallCategory);
    }

    @RequestMapping("/list")
    public Object list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<LitemallCategory> list = litemallCategoryService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return Result.success(pageInfo);
    }
}
