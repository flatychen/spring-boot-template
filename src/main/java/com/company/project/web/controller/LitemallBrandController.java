package com.company.project.web.controller;
import com.company.project.base.Result;
import com.company.project.base.ResultCodeEnum;
import com.company.project.model.LitemallBrand;
import com.company.project.service.LitemallBrandService;
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
@RequestMapping("/litemall/brand")
public class LitemallBrandController {
    @Resource
    private LitemallBrandService litemallBrandService;

    @RequestMapping("/add")
    public Object add(LitemallBrand litemallBrand) {
        litemallBrandService.save(litemallBrand);
        return Result.success();
    }

    @RequestMapping("/delete")
    public Object delete(@RequestParam Integer id) {
        litemallBrandService.deleteById(id);
        return Result.success();
    }

    @RequestMapping("/update")
    public Object update(LitemallBrand litemallBrand) {
        litemallBrandService.update(litemallBrand);
        return Result.success();
    }

    @RequestMapping("/detail")
    public Object detail(@RequestParam Integer id) {
        LitemallBrand litemallBrand = litemallBrandService.getById(id);
        return Result.success(litemallBrand);
    }

    @RequestMapping("/list")
    public Object list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<LitemallBrand> list = litemallBrandService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return Result.success(pageInfo);
    }
}
