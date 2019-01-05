package com.company.project.web.controller;
import com.company.project.base.Result;
import com.company.project.base.ResultCodeEnum;
import com.company.project.model.LuckyBag;
import com.company.project.service.LuckyBagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGen.CodeGenerator on 2019/01/05.
*/
@RestController
@RequestMapping("/lucky/bag")
public class LuckyBagController {
    @Resource
    private LuckyBagService luckyBagService;

    @RequestMapping("/add")
    public Object add(LuckyBag luckyBag) {
        luckyBagService.save(luckyBag);
        return Result.success();
    }

    @RequestMapping("/delete")
    public Object delete(@RequestParam Integer id) {
        luckyBagService.deleteById(id);
        return Result.success();
    }

    @RequestMapping("/update")
    public Object update(LuckyBag luckyBag) {
        luckyBagService.update(luckyBag);
        return Result.success();
    }

    @RequestMapping("/detail")
    public Object detail(@RequestParam Integer id) {
        LuckyBag luckyBag = luckyBagService.getById(id);
        return Result.success(luckyBag);
    }

    @RequestMapping("/list")
    public Object list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<LuckyBag> list = luckyBagService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return Result.success(pageInfo);
    }
}
