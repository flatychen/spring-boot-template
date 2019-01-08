package com.company.project.web.controller;
import com.company.project.base.Result;
import com.company.project.base.ResultCodeEnum;
import com.company.project.model.LitemallGoods;
import com.company.project.service.LitemallGoodsService;
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
@RequestMapping("/litemall/goods")
public class LitemallGoodsController {
    @Resource
    private LitemallGoodsService litemallGoodsService;

    @RequestMapping("/add")
    public Object add(LitemallGoods litemallGoods) {
        litemallGoodsService.save(litemallGoods);
        return Result.success();
    }

    @RequestMapping("/delete")
    public Object delete(@RequestParam Integer id) {
        litemallGoodsService.deleteById(id);
        return Result.success();
    }

    @RequestMapping("/update")
    public Object update(LitemallGoods litemallGoods) {
        litemallGoodsService.update(litemallGoods);
        return Result.success();
    }

    @RequestMapping("/detail")
    public Object detail(@RequestParam Integer id) {
        LitemallGoods litemallGoods = litemallGoodsService.getById(id);
        return Result.success(litemallGoods);
    }

    @RequestMapping("/list")
    public Object list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<LitemallGoods> list = litemallGoodsService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return Result.success(pageInfo);
    }
}
