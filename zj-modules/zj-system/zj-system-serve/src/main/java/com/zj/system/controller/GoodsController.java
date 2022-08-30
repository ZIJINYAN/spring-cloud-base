package com.zj.system.controller;

import com.zj.common.core.domain.Result;
import com.zj.system.common.domain.vo.GoodsItemVo;
import com.zj.system.common.domain.request.GoodsSearchVo;
import com.zj.system.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zj
 * @create 2022-08-28 20:45
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    @PostMapping("/list")
    public Result<List<GoodsItemVo>> goodsList(@RequestBody GoodsSearchVo goodsSearchVo){
        return Result.success(goodsService.goodsList(goodsSearchVo),"成功");
    }

    @PostMapping("/addCart/{goodsId}")
    public Result<?> addCart(@PathVariable("goodsId")Integer goodsId){
        return Result.success();
    }
}
