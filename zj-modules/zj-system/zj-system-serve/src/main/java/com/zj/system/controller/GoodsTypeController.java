package com.zj.system.controller;

import com.zj.system.service.IGoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zj
 * @create 2022-08-28 20:48
 */
@RestController
@RequestMapping("/goods/type")
public class GoodsTypeController {
    @Autowired
    private IGoodsTypeService goodsTypeService;
}
