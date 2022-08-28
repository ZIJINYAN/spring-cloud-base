package com.zj.cart.controller;

import com.zj.cart.service.ICartGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zj
 * @create 2022-08-28 21:08
 */
@RestController
@RequestMapping("/goods")
public class CartGoodsController {

    @Autowired
    private ICartGoodsService cartGoodsService;
}
