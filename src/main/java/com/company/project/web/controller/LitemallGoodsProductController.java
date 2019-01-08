package com.company.project.web.controller;
import com.company.project.base.Result;
import com.company.project.base.ResultCodeEnum;
import com.company.project.model.LitemallGoodsProduct;
import com.company.project.service.LitemallGoodsProductService;
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
@RequestMapping("/litemall/goods/product")
public class LitemallGoodsProductController {
    @Resource
    private LitemallGoodsProductService litemallGoodsProductService;

    @RequestMapping("/add")
    public Object add(LitemallGoodsProduct litemallGoodsProduct) {
        litemallGoodsProductService.save(litemallGoodsProduct);
        return Result.success();
    }

    @RequestMapping("/delete")
    public Object delete(@RequestParam Integer id) {
        litemallGoodsProductService.deleteById(id);
        return Result.success();
    }

    @RequestMapping("/update")
    public Object update(LitemallGoodsProduct litemallGoodsProduct) {
        litemallGoodsProductService.update(litemallGoodsProduct);
        return Result.success();
    }

    @RequestMapping("/detail")
    public Object detail(@RequestParam Integer id) {
        LitemallGoodsProduct litemallGoodsProduct = litemallGoodsProductService.getById(id);
        return Result.success(litemallGoodsProduct);
    }

    @RequestMapping("/list")
    public Object list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<LitemallGoodsProduct> list = litemallGoodsProductService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return Result.success(pageInfo);
    }
}
