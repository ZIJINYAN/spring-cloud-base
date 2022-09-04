package com.zj.system.controller;

import com.zj.common.core.domain.Result;
import com.zj.system.common.domain.GoodsTypeEntity;
import com.zj.system.service.IGoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zj
 * @create 2022-08-28 20:48
 */
@RestController
@RequestMapping("/goods/type")
public class GoodsTypeController {
    @Autowired
    private IGoodsTypeService goodsTypeService;

    @GetMapping("/list")
    public Result<List<GoodsTypeEntity>> goodsTypeList(){
        return Result.success(goodsTypeService.goodsTypeList());
    }
}
