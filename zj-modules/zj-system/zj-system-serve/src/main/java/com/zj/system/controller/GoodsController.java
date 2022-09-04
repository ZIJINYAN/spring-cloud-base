package com.zj.system.controller;


import com.zj.common.core.domain.PageResult;
import com.zj.common.core.domain.Result;
import com.zj.system.common.request.GoodsRequest;
import com.zj.system.common.vo.GoodsItemVo;
import com.zj.system.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result<PageResult<GoodsItemVo>> goodsEsList(@RequestBody GoodsRequest goodsRequest){
        return goodsService.goodsEsList(goodsRequest);
    }

}


